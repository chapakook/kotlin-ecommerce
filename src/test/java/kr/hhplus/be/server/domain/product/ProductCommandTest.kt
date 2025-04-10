package kr.hhplus.be.server.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ProductCommandTest {
    @Nested
    inner class Find{
        @Test
        fun `happy - 상품 아이디가 정수 일때 정상 반환한다`(){
            // given
            val productId = 1L
            // when
            val cmd = ProductCommand.Find(productId)
            // then
            assertThat(cmd.productId).isEqualTo(productId)
        }
        @Test
        fun `bad - 상품 아이디가 0 에러 을 반환한다`(){
            // given & when & then
            assertThatThrownBy { ProductCommand.Find(0L) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }

        @Test
        fun `bad - 상품 아이디가 음수 일때 에러 을 반환한다`() {
            // given & when & then
            assertThatThrownBy { ProductCommand.Find(-1L) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Nested
    inner class Reduce {
        @Test
        fun `happy - 상품 아이디 양수 일때 정상 반환한다` (){
            // given
            val productId = 1L
            val quantity = 10
            // when
            val cmd = ProductCommand.Reduce(productId, quantity)
            // then
            assertThat(cmd.productId).isEqualTo(productId)
        }
        @Test
        fun `happy - 차감 재고가 0 보다 크면 정상 반환한다` (){
            // given
            val productId = 1L
            val quantity = 10
            val cmd = ProductCommand.Reduce(productId, quantity)
            // then
            assertThat(cmd.productId).isEqualTo(productId)
        }
        @Test
        fun `bad - 상품 아이디가 0 에러 을 반환한다`(){
            // given & when & then
            assertThatThrownBy { ProductCommand.Reduce(0L, 100) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 상품 아이디 음수 일때 에러 반환한다` (){
            // given & when & then
            assertThatThrownBy { ProductCommand.Reduce(-1L,100) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 차감 개수가 음수 일때 에러 반환한다` (){
            // given & when & then
            assertThatThrownBy { ProductCommand.Reduce(1L,-100) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 차감 개수가 0 일때 에러 반환한다` (){
            // given & when & then
            assertThatThrownBy { ProductCommand.Reduce(1L,0) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}