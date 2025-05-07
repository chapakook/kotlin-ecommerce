package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.point.PointCommand
import kr.hhplus.be.server.domain.point.PointService
import kr.hhplus.be.server.domain.stock.StockCommand
import kr.hhplus.be.server.domain.stock.StockService
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.atomic.AtomicInteger
import kotlin.concurrent.thread

@SpringBootTest
class OrderFacadeConcurrencyTest {
    @Autowired
    private lateinit var orderFacade: OrderFacade

    @Autowired
    private lateinit var pointService: PointService

    @Autowired
    private lateinit var stockService: StockService

    @Test
    fun `happy - 주문 동시성 테스트`() {
        // given
        val count = 10
        val latch = CountDownLatch(count)
        val done = CountDownLatch(1)
        val success = AtomicInteger(0)
        val failure = AtomicInteger(0)
        val userId = 1L
        val productId = 1L
        val quantity = 10
        val cri = OrderCriteria.Order(userId, productId, quantity, null)
        val orders: MutableList<OrderResult.Order> = mutableListOf()
        val point = pointService.find(PointCommand.Find(userId))
        val stock = stockService.find(StockCommand.Find(productId))
        // when
        repeat(count) {
            thread {
                try {
                    done.await()
                    orders.add(orderFacade.order(cri))
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
        var amount = 0L
        orders.forEach { order -> amount += order.totalAmount }
        val resultPoint = pointService.find(PointCommand.Find(userId))
        val resultStock = stockService.find(StockCommand.Find(productId))
        // then
        assertThat(resultPoint.balance).isEqualTo(point.balance - amount)
        assertThat(resultStock.quantity).isEqualTo(stock.quantity - quantity * success.get())
    }
}