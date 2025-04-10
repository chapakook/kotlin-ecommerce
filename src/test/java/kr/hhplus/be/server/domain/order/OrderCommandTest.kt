package kr.hhplus.be.server.domain.order

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class OrderCommandTest {
    @Nested
    inner class Order {
        @Test
        fun `happy - 상품 아이디 양수, 가격 양수, 개수 양수 면 정상반환한다`(){
            // given
            val productId = 1L
            val price = 100L
            val quantity = 1
            // when
            val cmd = OrderCommand.Order(productId,price,quantity)
            // then
            assertThat(cmd.productId).isEqualTo(productId)
        }
        @Test
        fun `bad - 상품 아이디가 0 이면 에러를 반환한다`(){
            // given & when & then
            assertThatThrownBy { OrderCommand.Order(0L, 100L, 1) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 가격이 0 이면 에러를 반환한다`(){
            // given & when & then
            assertThatThrownBy { OrderCommand.Order(1L, 0L, 1) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 개수가 0 이면 에러를 반환한다`(){
            // given & when & then
            assertThatThrownBy { OrderCommand.Order(1L, 100L, 0) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}