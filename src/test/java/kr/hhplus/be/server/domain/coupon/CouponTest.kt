package kr.hhplus.be.server.domain.coupon

import kr.hhplus.be.server.support.ErrorCode.*
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class CouponTest {

    @Nested
    inner class Issue {
        @Test
        fun `happy - 쿠폰을 발급할 수 있다`() {
            // given
            val userId = 123L
            val type = CouponType.FIXED
            val value = 1000L
            val expiryMillis = LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC).toEpochMilli()
            // when
            val coupon = Coupon.issue(userId, type, value, expiryMillis)
            // then
            with(coupon) {
                assertThat(this.userId).isEqualTo(userId)
                assertThat(this.type).isEqualTo(type)
                assertThat(this.value).isEqualTo(value)
                assertThat(this.isActive).isEqualTo(true)
            }
        }

        @Test
        fun `happy - 당일 쿠폰을 발급할 수 있다`() {
            // given
            val userId = 123L
            val type = CouponType.FIXED
            val value = 1000L
            val expiryMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            // when
            val coupon = Coupon.issue(userId, type, value, expiryMillis)
            // then
            with(coupon) {
                assertThat(this.userId).isEqualTo(userId)
                assertThat(this.type).isEqualTo(type)
                assertThat(this.value).isEqualTo(value)
                assertThat(this.isActive).isEqualTo(true)
            }
        }

        @Test
        fun `bad - 오늘보다 이전의 만료일은 발급하려고 하면 IllegalArgumentException을 반환한다`() {
            // given
            val userId = 1234L
            val type = CouponType.FIXED
            val value = 1000L
            val expiryMillis = LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC).toEpochMilli()
            // when & then
            assertThatThrownBy { Coupon.issue(userId, type, value, expiryMillis) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessageContainingAll(EXPIRED_COUPON_EVENT.message)
        }
    }

    @Nested
    inner class Use {
        @Test
        fun `happy - 정액 쿠폰이 정상 적용된다`() {
            // given
            val coupon = Coupon.issue(
                123L,
                CouponType.FIXED,
                500L,
                LocalDateTime.now().plusDays(20).toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            // when
            val remainingAmount = coupon.use(1000L)
            // then
            assertThat(remainingAmount).isEqualTo(500L)
            assertThat(coupon.isActive).isTrue()
            assertThat(coupon.updateMillis).isPositive()
        }

        @Test
        fun `happy - 정률 쿠폰이 정상 적용된다`() {
            // given
            val coupon = Coupon.issue(
                123L,
                CouponType.RATE,
                20L,
                LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            // when
            val remainingAmount = coupon.use(1000L)
            // then
            assertThat(remainingAmount).isEqualTo(800L) // 20% discount on 1000 is 200
            assertThat(coupon.isActive).isTrue()
            assertThat(coupon.updateMillis).isPositive()
        }

        @Test
        fun `bad - 만료된 쿠폰은 사용시 IllegalArgumentException 을 반환한다`() {
            // given
            val date = LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC).toEpochMilli()
            val coupon = Coupon(
                123L,
                444L,
                CouponType.FIXED,
                500L,
                date,
                date,
                date,
                true
            )
            // when & then
            assertThatThrownBy { coupon.use(1000L) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessageContainingAll(EXPIRED_COUPON.message)
        }

        @Test
        fun `bad - 사용된 쿠폰은 사용시 IllegalArgumentException 반환한다`() {
            // given
            val coupon = Coupon.issue(
                123L,
                CouponType.FIXED,
                500L,
                LocalDateTime.now().plusDays(1).toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            coupon.isActive = false
            // when & then
            assertThatThrownBy { coupon.use(1000L) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessageContainingAll(USED_COUPON.message)
        }
    }
}