package kr.hhplus.be.server.domain.order

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderServiceTest {
    private val orderRepository = mockk<OrderRepository>()
    private val orderService = OrderService(orderRepository)

    @Test
    fun `happy - 주문에 성공한다`() {
        // given
        val userId = 2L
        val productId = 3L
        val quantity = 10
        val name = "test"
        val price = 1000L
        val cmd = OrderCommand.Order(
            userId = userId,
            productId = productId,
            quantity = quantity,
            name = name,
            price = price,
        )
        val order = Order(
            userId = userId,
            productId = productId,
            quantity = quantity,
            totalAmount = price * quantity,
            paymentAmount = price * quantity,
            createMillis = System.currentTimeMillis(),
        )
        every { orderRepository.save(any()) } returns order
        // when
        val result = orderService.order(cmd)
        // then
        assertThat(result.orderId).isEqualTo(order.orderId)
        assertThat(result.userId).isEqualTo(order.userId)
        assertThat(result.productId).isEqualTo(order.productId)
        assertThat(result.quantity).isEqualTo(order.quantity)
        assertThat(result.totalAmount).isEqualTo(order.totalAmount)
        verify(exactly = 1) { orderRepository.save(any()) }
    }
}