package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.application.coupon.CouponCriteria
import kr.hhplus.be.server.application.coupon.CouponFacade
import kr.hhplus.be.server.support.RedisPrefix.CACHE_COUPON_EVENTS
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CouponControllerE2ETest {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Autowired
    private lateinit var couponFacade: CouponFacade

    @Autowired
    private lateinit var couponIssueRunner: CouponIssueRunner

    @Autowired
    private lateinit var redisTemplate: RedisTemplate<String, String>

    @Test
    fun `POST - 선착순 쿠폰 발급 E2E 테스트`() {
        // given
        val userId = 1L
        val couponId = 1L
        val baseUri = "http://localhost:$port/coupon/issue"
        val req = CouponRequest.Issue(userId, couponId)
        // when
        val resp: ResponseEntity<CouponResponse.IssueV1> =
            restTemplate.postForEntity(baseUri, req, CouponResponse.IssueV1::class.java)
        // then
        assertThat(resp.statusCode).isEqualTo(HttpStatus.OK)
    }

    @Test
    fun `스케줄러가 Redis 대기열에서 유저를 꺼내 실제로 쿠폰을 발급해야 한다`() {
        // given
        val userIds = (1L..5L)
        val couponEventId = 5L
        userIds.forEach { userId ->
            couponFacade.enqueue(CouponCriteria.Enqueue(couponEventId, userId))
        }

        // when
        couponIssueRunner.start(couponEventId, "*/1 * * * * *")
        Thread.sleep(4000)
        couponIssueRunner.stop(couponEventId)

        // then
        val issued = redisTemplate.opsForSet().members("${CACHE_COUPON_EVENTS.prefix}:$couponEventId")
        assertThat(issued).hasSize(5)
    }
}