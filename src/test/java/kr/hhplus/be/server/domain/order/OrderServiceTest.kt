package kr.hhplus.be.server.domain.order

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import java.time.LocalDateTime

class OrderServiceTest {
    private lateinit var orderService: OrderService
    private lateinit var orderRepository: OrderRepository

    @BeforeEach
    fun setUp() {
        orderRepository = mockk()
        orderService = OrderService(orderRepository)
    }

    @Nested
    inner class Order{
        @Test
        fun `happy - orderCmd 이용 정상적인 주문 요청시 주문된다`() {
            // given
            val productId = 1L
            val price = 2000L
            val quantity = 1
            val orderId = 2L
            val orderCmd = OrderCommand.Order(productId, price, quantity)
            val fakeOrder = OrderInfo.Order(orderId, productId, quantity, quantity*price, LocalDateTime.now())
            every { orderRepository.getNextOrderId() } returns orderId
            // when
            val result = orderService.order(orderCmd)
            // then
            assertThat(result)
                .extracting("orderId", "productId","quantity","totalPrice")
                .containsExactly(orderId,productId,quantity,quantity*price)
        }
    }
}