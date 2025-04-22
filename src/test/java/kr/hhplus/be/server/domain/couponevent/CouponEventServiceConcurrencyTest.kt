package kr.hhplus.be.server.domain.couponevent

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

@SpringBootTest
class CouponEventServiceConcurrencyTest {
    @Autowired
    private lateinit var couponEventService: CouponEventService

    @Nested
    inner class Issue {
        @Test
        fun `happy - 쿠폰 이벤트 차감 동시성 테스트`() {
            // given
            val count = 10
            val service = Executors.newFixedThreadPool(5)
            val latch = CountDownLatch(count)
            val couponEventId = 1L
            val cmd = CouponEventCommand.Find(couponEventId)
            val expected = couponEventService.find(cmd)
            val success = AtomicInteger(0)
            val failure = AtomicInteger(0)
            // when
            repeat(count) {
                service.execute {
                    try {
                        couponEventService.issue(CouponEventCommand.Issue(couponEventId))
                        success.incrementAndGet()
                    } catch (e: Exception) {
                        failure.incrementAndGet()
                    } finally {
                        latch.countDown()
                    }
                }
            }
            latch.await()
            service.shutdown()
            val result = couponEventService.find(cmd)
            // then
            assertThat(success.get()).isGreaterThanOrEqualTo(count)
            assertThat(failure.get()).isLessThanOrEqualTo(0)
            assertThat(result.currentCount).isEqualTo(expected.currentCount + success.get())
        }
    }
}