package kr.hhplus.be.server.domain.payment

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class PaymentCommandTest {
    @Nested
    inner class Payment{
        @Test
        fun `happy - 사용자 아이디는 양수이고 결제액이 0 보다크면 성공`(){
            // given
            val userId = 1L
            val totalAmount = 100L
            // when
            val cmd = PaymentCommand.Payment(userId, totalAmount)
            // then
            assertThat(cmd.userId).isEqualTo(userId)
        }@Test
        fun `happy - 사용자 아이디는 양수이고 결제액이 0이면 성공`(){
            // given
            val userId = 1L
            val totalAmount = 0L
            // when
            val cmd = PaymentCommand.Payment(userId, totalAmount)
            // then
            assertThat(cmd.userId).isEqualTo(userId)
        }
        @Test
        fun `bad - 사용자 ID가 0 이면 실패`(){
            // given & when & then
            assertThatThrownBy {PaymentCommand.Payment(0L, 10L)}
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 사용자 ID가 0 보다 작으면 실패`(){
            // given & when & then
            assertThatThrownBy {PaymentCommand.Payment(-1L, 10L)}
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 결제 금액이 0 미만이면 실패`(){
            // given & when & then
            assertThatThrownBy {PaymentCommand.Payment(1L, -1L)}
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}