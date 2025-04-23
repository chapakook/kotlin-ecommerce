package kr.hhplus.be.server.domain.stock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.atomic.AtomicInteger

@SpringBootTest
class StockServiceConcurrencyTest {
    @Autowired
    private lateinit var stockService: StockService

    @Nested
    inner class Deduct {
        @Test
        fun `happy - 재고 차감 동시성 테스트`() {
            // given
            val count = 10
            val service = Executors.newFixedThreadPool(5)
            val latch = CountDownLatch(count)
            val success = AtomicInteger(0)
            val failure = AtomicInteger(0)
            val productId = 1L
            val quantity = 1
            val findCmd = StockCommand.Find(productId)
            val expected = stockService.find(findCmd)
            // when
            repeat(count) {
                service.submit {
                    try {
                        stockService.deduct(StockCommand.Deduct(productId, quantity))
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
            val result = stockService.find(findCmd)
            // then
            assertThat(result.quantity).isEqualTo(expected.quantity - count)
        }
    }
}