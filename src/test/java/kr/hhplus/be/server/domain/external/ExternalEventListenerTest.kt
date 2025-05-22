package kr.hhplus.be.server.domain.external

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.stock.StockEvent
import org.junit.jupiter.api.Test

class ExternalEventListenerTest {
    private val externalNotify = mockk<ExternalNotify>()
    private val externalEventListener = ExternalEventListener(externalNotify)

    @Test
    fun `happy - 주문완료이벤트 수신받아 externalNotify_notify 를 수행한다`() {
        // given
        val orderId = 1L
        val userId = 1L
        val productId = 1L
        val quantity = 10
        val totalAmount = 10000L;
        val event = StockEvent.StockDeducted(orderId, userId, null, productId, quantity, totalAmount)
        every { externalNotify.notify(any()) } returns Unit
        // when
        externalEventListener.handler(event)
        // then
        verify(exactly = 1) { externalNotify.notify(any()) }
    }
}