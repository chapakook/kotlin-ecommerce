package kr.hhplus.be.server.application.coupon

import kr.hhplus.be.server.domain.couponevent.CouponEventCommand
import kr.hhplus.be.server.domain.couponevent.CouponEventService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

@SpringBootTest
class CouponFacadeConcurrencyTest {
    @Autowired
    private lateinit var couponFacade: CouponFacade

    @Autowired
    private lateinit var couponEventService: CouponEventService

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
}