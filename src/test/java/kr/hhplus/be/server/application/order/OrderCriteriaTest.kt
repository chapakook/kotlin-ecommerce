package kr.hhplus.be.server.application.order

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.*

class OrderCriteriaTest{
    @Nested
    inner class Order {
        @Test
        fun `happy - 사용자아이디, 상품아이디, 단가, 수량 모두 양수면 성공`(){
            // given
            val userId = 1L
            val productId = 2L
            val price = 3L
            val quantity = 4
            val expended = OrderCriteria.Order(userId, productId, price, quantity, null)
            // when
            val cri = OrderCriteria.Order(userId, productId, price, quantity, null)
            // then
            assertThat(cri).isEqualTo(expended)
        }
        @Test
        fun `happy - 사용자아이디, 상품아이디, 단가, 수량, 모두 양수 + 쿠폰 까지 있으면 성곤`() {
            // given
            val userId = 1L
            val productId = 2L
            val price = 3L
            val quantity = 4
            val code = UUID.randomUUID().toString()
            val expended = OrderCriteria.Order(userId, productId, price, quantity, code)
            // when
            val cri = OrderCriteria.Order(userId, productId, price, quantity, code)
            // then
            assertThat(cri).isEqualTo(expended)
        }
    }
}