package kr.hhplus.be.server.domain.order

import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

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
            val quantity = 1
            val orderCmd = OrderCommand.Order(productId, quantity)
            val fakeOrder = OrderInfo.Order(1L)
            // when
            val result = orderService.order(orderCmd)
            // then
            assertThat(result).isEqualTo(fakeOrder)
        }
    }
}