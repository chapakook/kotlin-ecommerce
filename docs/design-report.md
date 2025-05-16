## 배경
사용자 수가 증가함에 따라 인기 상품 조회와 선착순 쿠폰 발급 기능에서 성능 저하 현상이 발생하고 있습니다.
기존에는 단순 RDB 기반의 정렬 및 발급 로직으로 처리했지만, 트래픽 증가에 따른 병목과 동시성 이슈가 반복되고 있습니다.
이에 따라 고성능 데이터 처리를 위한 Redis 기반 구조 도입을 통해 시스템의 응답성과 안정성을 개선하고자 합니다.

## 분석 및 문제점
### 인기상품조회
실시간 트래픽이 집중되며 조회수 순 정렬 쿼리로 인해 DB 부하 심화
다중 join, 정렬 연산이 잦아 응답 지연 발생
정렬 기준 데이터가 DB에 실시간 반영되지 않아 랭킹 정확도 저하 가능

### 선착순쿠폰발급
사용자가 동시에 요청할 경우 DB 락 경합이 발생하여 응답 지연 또는 실패 가능성
중복 발급, race condition 문제 발생
쿠폰 수량 제한 로직의 일관성 보장 어려움

## 설계
### 인기상품조회
인기 상품 조회는 사용자 트래픽이 몰리는 핵심 기능으로, 실시간 조회수 기반 정렬이 필요합니다.
기존 RDB 기반 정렬 쿼리의 부하를 줄이고, 빠른 응답을 보장하기 위해 Redis `ZSet`을 활용한 캐싱 구조로 전환했습니다.
- 현재 집계는 상품판매 수량으로만 집계를 진행중이며, 상품을 구매 완료수 집계 수량을 업데이트하는 방향으로 설계를 진행했습니다.
- 이렇게 집계된 데이터는 `ZUNION`을 통해 인기상품의 기준인 3일 치의 합집합을 구한 후 응답합니다
- `TTL`은 집계데이터는 3일치만 필요함으로 `3d`로 구성하였습니다.
- 인기상품의 경우 휘발성 데이터라고 판단. 영속화를 진해하지 않았습니다.

### 인터페이스의 활용
```kotlin
interface RankingTarget {
    val productId: Long
    val quantity: Int
}
```
인터페이스를 활용하여, 인기상품요건을 구체화하여 변경할 수 있도록 구성했습니다.

### 랭킹증가 -> AOP로 구성
```kotlin
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class IncreasesProductRanking

@Aspect
@Component
class IncreasesProductRankingAspect(
    private val productRankRepository: ProductRankingRepository
) {
    @AfterReturning("@annotation(kr.hhplus.be.server.support.annotation.IncreasesProductRanking)")
    fun afterReturning(joinPoint: JoinPoint) {
        if (!TransactionSynchronizationManager.isSynchronizationActive()) return
        TransactionSynchronizationManager.registerSynchronization(object : TransactionSynchronization {
            override fun afterCommit() {
                joinPoint.args
                    .filterIsInstance<RankingTarget>()
                    .forEach {
                        productRankRepository.increaseProductRank(
                            productId = it.productId,
                            scoreDelta = it.quantity.toDouble()
                        )
                    }
            }
        })
    }
}
```
커스텀어노테이션을 통해 기존로직에 영향없이 랭킹에 영향도가 있는 데이터를 수정할 수 있었습니다.

### 선착순쿠폰발급
선착순 쿠폰 발급은 사용자 요청이 폭주할 수 있는 이벤트성 기능입니다. 
발급 순서를 보장하면서도 성능 저하를 방지하기 위해 대기열은 Redis `ZSet`으로 구성하고, 실제 발급은 스케줄러에 의해 순차적으로 처리됩니다.
- 신속한 요청 처리를 위해 `쿠폰발급요청`이 들어오면 `Queue`에 작업을 적제합니다.
- `ZSet`을 이용, 중복처리를 방지했습니다. 
- `쿠폰발급처리`의 경우 일정주기로 발급큐를 스케줄링하며 쿠폰 발급을 진행합니다.
- `쿠폰발급처리`시 DB에 영속화도 같이 진행합니다.


### Redis Key 설계
```kotlin
enum class RedisPrefix(val prefix: String) {
    CACHE_PRODUCTS("products"),

    CACHE_RANK("rank"),
    CACHE_PRODUCTS_RANK("${CACHE_RANK.prefix}:${CACHE_PRODUCTS.prefix}"),
    CACHE_PRODUCT_RANK_DAILY("${CACHE_PRODUCTS_RANK.prefix}:daily"),

    CACHE_QUEUE("queue"),
    CACHE_COUPONS("coupons"),
    CACHE_COUPON_EVENTS("coupon-events"),
    CACHE_QUEUE_COUPON_EVENTS("${CACHE_QUEUE.prefix}:${CACHE_COUPON_EVENTS.prefix}"),
}
```
enum class를 활용, 한곳에서 관리하여 키값을 유실 방지를 였습니다.

## Redis의 불안정성에 대한 고려 사항
현재의 구조는 redis에 많이 의존하고 있는 구조이며, redis 장애시 시스템 장애로 아주 큰 문제가 발생할 수 있을 것 으로 예상됩니다.

단일 장애점(SPOF): Redis가 단일 노드로 운영될 경우 장애 발생 시 전체 쿠폰 발급이나 인기 상품 조회 기능이 멈출 수 있음

메모리 기반 저장소의 특성상 휘발성 위험 존재: 영속성 설정 없이 사용 시 장애 복구 후 데이터 유실 가능
네트워크 분리 또는 클러스터 파편화: 클러스터 모드에서 분산 노드 간 네트워크 이슈 발생 시 일부 key에 접근 불가

Failover 시 일관성 문제: Sentinel 또는 Cluster 기반 Redis 사용 시 자동 장애 조치 중 데이터 정합성 문제가 발생할 수 있음
과부하 시 성능 저하: TTL 설정 없이 ZSet에 계속 쌓일 경우 메모리 부족 또는 성능 저하 가능

> 💡 이러한 위험 요소를 완화하기 위해, 장애 복구 시나리오, Redis 운영 설정 최적화, 모니터링 및 알림 . 체계 마련이 필요합니다.

## 결론
Redis 기반의 구조 개선을 통해 기존 RDB 중심의 선착순 쿠폰 발급 및 인기 상품 조회 처리 방식에서 발생하던 성능 병목을 상당 부분 해소할 수 있었습니다.
- 유저 요청 → Redis 대기열 → 스케줄 발급 → DB 저장의 안정적인 파이프라인 구성
- 인기 상품 조회의 실시간성 및 정렬 응답 개선

하지만 Redis를 단일 의존 요소로 두고 있는 구조적 특성상, 다음과 같은 보완 과제가 여전히 존재합니다:
- Redis 장애 시 전체 기능 마비 위험 → 이중화 및 fallback 전략 필요
- 메모리 기반 시스템의 휘발성과 장애 복구 후 정합성 보장 문제
- 트래픽 급증 시 Redis 부하 분산 전략 부족

따라서 성능 개선이라는 1차 목표는 달성했지만, 장기적으로는 안정성과 복구 가능성을 함께 고려한 고도화가 추가로 필요해보입니다.