package kr.hhplus.be.server.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ProductCommandTest {
    @Nested
    inner class Get{
        @Test
        fun `happy - productId가 정수 일때 정상 반환한다`(){
            // given
            val productId = 1L
            // when
            val cmd = ProductCommand.Get(productId)
            // then
            assertThat(cmd.productId).isEqualTo(productId)
        }
        @Test
        fun `bad - productId가 0 IllegalArgumentException 을 반환한다`(){
            // given & when & then
            assertThatThrownBy { ProductCommand.Get(0L) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }

        @Test
        fun `bad - productId가 음수 일때 IllegalArgumentException 을 반환한다`() {
            // given & when & then
            assertThatThrownBy { ProductCommand.Get(-1L) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}