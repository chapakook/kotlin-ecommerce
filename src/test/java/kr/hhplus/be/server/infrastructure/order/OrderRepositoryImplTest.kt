package kr.hhplus.be.server.infrastructure.order

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderRepositoryImplTest {
    private val orderJPARepository = mockk<OrderJPARepository>()
    private val orderRepository = OrderRepositoryImpl(orderJPARepository)

    @Test
    fun `happy - 주문을 저장하면 주문을 반환함`() {
        // given
        val entity = OrderEntity(1L, 2L, 3L, 10, 3000, 2000, System.currentTimeMillis())
        every { orderJPARepository.save(any()) } returns entity
        // when
        val result = orderRepository.save(entity.to())
        // then
        assertThat(result.orderId).isEqualTo(entity.orderId)
        assertThat(result.userId).isEqualTo(entity.userId)
        assertThat(result.productId).isEqualTo(entity.productId)
        assertThat(result.quantity).isEqualTo(entity.quantity)
        assertThat(result.totalAmount).isEqualTo(entity.totalAmount)
        assertThat(result.paymentAmount).isEqualTo(entity.paymentAmount)
        assertThat(result.createMillis).isEqualTo(entity.createMillis)
        verify(exactly = 1) { orderJPARepository.save(any()) }
    }
}