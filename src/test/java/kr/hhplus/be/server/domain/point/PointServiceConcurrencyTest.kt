package kr.hhplus.be.server.domain.point

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

@SpringBootTest
class PointServiceConcurrencyTest {
    @Autowired
    private lateinit var pointService: PointService

    @Nested
    inner class Charge {
        @Test
        fun `happy - 포인트 충전 동시성 테스트`() {
            // given
            val userId = 4L
            val amount = 100L
            val count = 10
            val latch = CountDownLatch(count)
            val done = CountDownLatch(1)
            val expected = pointService.find(PointCommand.Find(userId))
            val success = AtomicInteger(0)
            val failure = AtomicInteger(0)
            // when
            repeat(count) {
                thread {
                    try {
                        done.await()
                        pointService.charge(PointCommand.Charge(userId, amount))
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
            val result = pointService.find(PointCommand.Find(userId))
            // then
            assertThat(result.balance).isEqualTo(expected.balance + amount * success.get())
        }
    }

    @Nested
    inner class Use {
        @Test
        fun `happy - 포인트 사용 동시성 테스트`() {
            // given
            val userId = 5L
            val amount = 100L
            val count = 10
            val latch = CountDownLatch(count)
            val done = CountDownLatch(1)
            val expected = pointService.find(PointCommand.Find(userId))
            val success = AtomicInteger(0)
            val failure = AtomicInteger(0)
            // when
            repeat(count) {
                thread {
                    try {
                        done.await()
                        pointService.use(PointCommand.Use(userId, amount))
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
            val result = pointService.find(PointCommand.Find(userId))
            // then
            assertThat(result.balance).isEqualTo(expected.balance - amount * success.get())
        }
    }
}