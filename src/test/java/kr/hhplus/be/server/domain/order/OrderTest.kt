package kr.hhplus.be.server.domain.order

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OrderTest {
    @Test
    fun `happy - 오더생성 성공`() {
        // given
        val orderId = 10L
        val userId = 1L
        val productId = 2L
        val quantity = 10
        val price = 10L
        val total = quantity * price
        val amount = 10L
        // when
        val result = Order(orderId, userId, productId, quantity, total, amount)
        // then
        assertThat(result.orderId).isEqualTo(orderId)
    }
}