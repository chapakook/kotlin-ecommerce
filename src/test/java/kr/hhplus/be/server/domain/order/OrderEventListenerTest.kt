package kr.hhplus.be.server.domain.order

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Test

class OrderEventListenerTest {
    private val externalNotify = mockk<ExternalNotify>()
    private val orderEventListener = OrderEventListener(externalNotify)

    @Test
    fun `happy - 주문완료이벤트 수신받아 externalNotify_notify 를 수행한다`() {
        // given
        val orderId = 1L
        val userId = 1L
        val productId = 1L
        val quantity = 10
        val totalAmount = 10000L;
        val event = OrderEvent.OrderCompleted(orderId, userId, productId, quantity, totalAmount)
        every { externalNotify.notify(any()) } returns Unit
        // when
        orderEventListener.handleNotify(event)
        // then
        verify(exactly = 1) { externalNotify.notify(any()) }
    }
}