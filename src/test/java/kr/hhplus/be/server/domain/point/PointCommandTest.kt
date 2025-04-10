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
        fun `happy - userId가 정수 일때 정상 반환한다`(){
            // given
            val userId = 1L
            // when
            val cmd = PointCommand.Get(userId)
            // then
            assertThat(cmd.userId).isEqualTo(userId)
        }

        @Test
        fun `bad - userId가 0 IllegalArgumentException 을 반환한다`(){
            // given & when & then
            assertThatThrownBy { PointCommand.Get(0L) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }

        @Test
        fun `bad - userId가 음수 일때 IllegalArgumentException 을 반환한다`() {
            // given & when & then
            assertThatThrownBy { PointCommand.Get(-1L) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }

    @Nested
    inner class Charge {
        @Test
        fun `happy - userId가 정수 일때 정상 반환한다`(){
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
        fun `bad - userId가 0 IllegalArgumentException 을 반환한다`(){
            // given
            val userId = 0L
            val amount = 100L
            // when & then
            assertThatThrownBy { PointCommand.Charge(userId,amount) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }

        @Test
        fun `bad - userId가 음수 일때 IllegalArgumentException 을 반환한다`() {
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
        fun `happy - userId가 정수 일때 정상 반환한다`(){
            // given
            val userId = 1L
            val amount = 100L
            // when
            val cmd = PointCommand.Use(userId,amount)
            // then
            assertThat(cmd)
                .extracting("userId","amount")
                .containsExactly(userId,amount)
        }

        @Test
        fun `bad - userId가 0 IllegalArgumentException 을 반환한다`(){
            // given
            val userId = 0L
            val amount = 100L
            // when & then
            assertThatThrownBy { PointCommand.Use(userId,amount) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }

        @Test
        fun `bad - userId가 음수 일때 IllegalArgumentException 을 반환한다`() {
            // given
            val userId = -1L
            val amount = 100L
            // when & then
            assertThatThrownBy { PointCommand.Use(userId,amount) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}