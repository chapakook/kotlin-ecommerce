package kr.hhplus.be.server.domain.coupon

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class CouponCommandTest {
    @Nested
    inner class Use {
        @Test
        fun `happy - 사용자아이디가 양수면 성공`(){
            // given
            val userId = 1L
            // when
            val cmd = CouponCommand.Use(userId, null)
            // then
            assertThat(cmd.userId).isEqualTo(userId)
        }
        @Test
        fun `bad - 사용자 아이디가 0 이면 실패` () {
            // given & when & then
            assertThatThrownBy { CouponCommand.Use(0L,null)}
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 사용자 아이디가 음수면 실패` () {
            // given & when & then
            assertThatThrownBy { CouponCommand.Use(-1L,null)}
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
    @Nested
    inner class Issue {
        @Test
        fun `happy - 사용자 아이디가 양수 이고, 쿠폰 아이디가 양수면 성공`(){
            // given
            val userId = 1L
            val couponId = 2L
            // when
            val cmd = CouponCommand.Issue(userId, couponId)
            // then
            assertThat(cmd.userId).isEqualTo(userId)
        }
        @Test
        fun `bad - 사용자 아이디가 0 이면 실패` () {
            // given & when & then
            assertThatThrownBy { CouponCommand.Issue(0L,1L)}
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 사용자 아이디가 음수면 실패` () {
            // given & when & then
            assertThatThrownBy { CouponCommand.Issue(-1L,1L)}
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 쿠폰 아아디가 0 이면 실패` () {
            // given & when & then
            assertThatThrownBy { CouponCommand.Issue(1L,0L)}
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 쿠폰 아이디가 음수면 실패` () {
            // given & when & then
            assertThatThrownBy { CouponCommand.Issue(1L,-1L)}
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}
