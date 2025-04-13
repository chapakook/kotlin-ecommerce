package kr.hhplus.be.server.domain.point

import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.reflect.full.memberProperties
import kotlin.reflect.typeOf

class PointCommandTest{
    @Nested
    inner class Get{
        @Test
        fun `happy - userId가 정수 일때 성공`(){
            // given
            val userId = 1L
            // when
            val cmd = PointCommand.Get(userId)
            // then
            assertThat(cmd.userId).isEqualTo(userId)
        }

        @Test
        fun `bad - userId가 0 실패`(){
            // given & when & then
            assertThatThrownBy { PointCommand.Get(0L) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }

        @Test
        fun `bad - userId가 음수 실패`() {
            // given & when & then
            assertThatThrownBy { PointCommand.Get(-1L) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Nested
    inner class Charge {
        @Test
        fun `happy - userId가 정수 일때 성공`(){
            // given
            val userId = 1L
            val amount = 100L
            // when
            val cmd = PointCommand.Charge(userId,amount)
            // then
            assertThat(cmd)
                .extracting("userId","amount")
                .containsExactly(userId,amount)
        }

        @Test
        fun `bad - userId가 0 실패`(){
            // given
            val userId = 0L
            val amount = 100L
            // when & then
            assertThatThrownBy { PointCommand.Charge(userId,amount) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }

        @Test
        fun `bad - userId가 음수 일때 실패`() {
            // given
            val userId = -1L
            val amount = 100L
            // when & then
            assertThatThrownBy { PointCommand.Charge(userId,amount) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Nested
    inner class Use {
        @Test
        fun `happy - userId가 정수 일때 성공`(){
            // given
            val userId = 1L
            val amount = 100L
            // when
            val cmd = PointCommand.Use(userId,amount, null)
            // then
            assertThat(cmd)
                .extracting("userId","amount")
                .containsExactly(userId,amount)
        }
        @Test
        fun `happy - 사용금액이 0 보다 클때 사용 성공`(){
            // given
            val userId = 1L
            val amount = 100L
            // when
            val cmd = PointCommand.Use(userId,amount, null)
            // then
            assertThat(cmd)
                .extracting("userId","amount")
                .containsExactly(userId,amount)
        }
        @Test
        fun `happy - 사용금액이 0일때 사용 성공`(){
            // given
            val userId = 1L
            val amount = 0L
            // when
            val cmd = PointCommand.Use(userId,amount, null)
            // then
            assertThat(cmd)
                .extracting("userId","amount")
                .containsExactly(userId,amount)
        }
        @Test
        fun `bad - userId가 0 실패`(){
            // given
            val userId = 0L
            val amount = 100L
            // when & then
            assertThatThrownBy { PointCommand.Use(userId,amount, null) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - userId가 음수 일때 사용 실패`() {
            // given
            val userId = -1L
            val amount = 100L
            // when & then
            assertThatThrownBy { PointCommand.Use(userId,amount, null) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 사용금액이 0 미만이면 사용 실패`() {
            // given
            val userId = 1L
            val amount = -1L
            // when & then
            assertThatThrownBy { PointCommand.Use(userId, amount, null) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}