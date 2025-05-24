package kr.hhplus.be.server.application.coupon

import kr.hhplus.be.server.domain.couponevent.CouponEventCommand
import kr.hhplus.be.server.domain.couponevent.CouponEventService
import kr.hhplus.be.server.support.RedisPrefix.CACHE_QUEUE_COUPON_EVENTS
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.data.redis.core.RedisTemplate
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

@SpringBootTest
class CouponFacadeConcurrencyTest(
    @Autowired private val couponFacade: CouponFacade,
    @Autowired private val couponEventService: CouponEventService,
    @Autowired private val redisTemplate: RedisTemplate<String, String>
) {
    @Test
    fun `happy - 쿠폰 발급 동시성 테스트`() {
        // given
        val count = 10
        val latch = CountDownLatch(count)
        val done = CountDownLatch(1)
        val success = AtomicInteger(0)
        val failure = AtomicInteger(0)
        val userId = 1L
        val couponEventId = 1L
        val cri = CouponCriteria.Issue(userId, couponEventId)
        val cmd = CouponEventCommand.Find(couponEventId)
        val expected = couponEventService.find(cmd)
        // when
        repeat(count) {
            thread {
                try {
                    done.await()
                    couponFacade.issue(cri)
                    success.incrementAndGet()
                } catch (e: Exception) {
                    failure.incrementAndGet()
                } finally {
                    latch.countDown()
                }
            }
        }
        done.countDown()
        latch.await()
        val result = couponEventService.find(cmd)
        // then
        assertThat(result.currentCount).isEqualTo(expected.currentCount + count)
    }

    @Test
    fun `happy - 쿠폰 발급 데기열 등록`() {
        // given
        val couponEventId = 3L
        val user = (1 until 21).map { it.toLong() }
        // when
        user.forEach { u -> couponFacade.enqueue(CouponCriteria.Enqueue(couponEventId, u)) }
        // then
        val key = "${CACHE_QUEUE_COUPON_EVENTS.prefix}:$couponEventId"
        val size = redisTemplate.opsForZSet().size(key)
        assertThat(size).isEqualTo(20)
        val enqueuedUsers = redisTemplate.opsForZSet()
            .range(key, 0, -1)
            ?.map { it.toLong() }
        assertThat(enqueuedUsers).containsExactlyElementsOf(user)
    }

}