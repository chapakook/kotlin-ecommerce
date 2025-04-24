package kr.hhplus.be.server.domain.couponevent

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

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
            val latch = CountDownLatch(count)
            val done = CountDownLatch(1)
            val couponEventId = 1L
            val cmd = CouponEventCommand.Find(couponEventId)
            val expected = couponEventService.find(cmd)
            val success = AtomicInteger(0)
            val failure = AtomicInteger(0)
            // when
            repeat(count) {
                thread {
                    try {
                        done.await()
                        couponEventService.issue(CouponEventCommand.Issue(couponEventId))
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
            println("success: ${success.get()}")
            println("failure: ${failure.get()}")
            assertThat(result.currentCount).isEqualTo(expected.currentCount + success.get())
        }
    }
}