package kr.hhplus.be.server.domain.order

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class OrderServiceTest {
    private lateinit var orderService: OrderService

    @BeforeEach
    fun setUp() {
        orderService = OrderService()
    }

    @Nested
    inner class Order{
        @Test
        fun `happy - orderCmd 이용 정상적인 주문 요청시 주문된다`() {
            // given
            val orderCmd = OrderCommand.Order()
            val fakeOrder = OrderInfo.Order(1L)
            // when
            val result = orderService.order(orderCmd)
            // then
            assertThat(result).isEqualTo(fakeOrder)
        }
    }
}