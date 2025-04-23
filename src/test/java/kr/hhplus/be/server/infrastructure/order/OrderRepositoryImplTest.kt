package kr.hhplus.be.server.infrastructure.order

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.order.Order
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderRepositoryImplTest {
    private val orderJPARepository = mockk<OrderJPARepository>()
    private val orderRepository = OrderRepositoryImpl(orderJPARepository)

    @Test
    fun `happy - 주문을 저장하면 주문을 반환함`() {
        // given
        val order = Order(1L, 2L, 3L, 10, 3000, 2000, 0)
        every { orderJPARepository.save(any()) } returns order
        // when
        val result = orderRepository.save(order)
        // then
        assertThat(result.orderId).isEqualTo(order.orderId)
        assertThat(result.userId).isEqualTo(order.userId)
        assertThat(result.productId).isEqualTo(order.productId)
        assertThat(result.quantity).isEqualTo(order.quantity)
        assertThat(result.totalAmount).isEqualTo(order.totalAmount)
        assertThat(result.paymentAmount).isEqualTo(order.paymentAmount)
        assertThat(result.createMillis).isEqualTo(order.createMillis)
        verify(exactly = 1) { orderJPARepository.save(any()) }
    }
}