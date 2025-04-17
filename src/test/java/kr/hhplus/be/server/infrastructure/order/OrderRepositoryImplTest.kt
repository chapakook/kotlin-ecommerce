package kr.hhplus.be.server.infrastructure.order

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.order.Order
import org.assertj.core.api.Assertions.assertThat
import kotlin.test.Test

class OrderRepositoryImplTest {
    private val orderJPARepository = mockk<OrderJPARepository>()
    private val orderRepository = OrderRepositoryImpl(orderJPARepository)

    @Test
    fun `happy - 주문을 저장하면 주문을 반환함`() {
        // given
        val order = Order(1L, 2L, 3L, 10, 3000, 2000)
        every { orderJPARepository.save(order) } returns order
        // when
        val result = orderRepository.save(order)
        // then
        assertThat(result).isEqualTo(order)
        verify(exactly = 1) { orderJPARepository.save(order) }
    }
}