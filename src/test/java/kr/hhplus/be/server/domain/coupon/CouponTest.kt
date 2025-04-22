package kr.hhplus.be.server.domain.coupon

import kr.hhplus.be.server.support.ErrorCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.time.ZoneOffset

class CouponTest {
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
        assertThat(coupon)
            .extracting("userId", "type", "value", "isActive")
            .containsExactly(userId, type, value, true)
    }

    @Test
    fun `happy - 정액 쿠폰이 정상 작동한다`() {
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
        val coupon = Coupon.issue(
            123L,
            CouponType.FIXED,
            500L,
            LocalDateTime.now().minusDays(1).toInstant(ZoneOffset.UTC).toEpochMilli()
        )
        // when & then
        val exception = assertThrows<IllegalArgumentException> { coupon.use(1000L) }
        assertThat(exception.message).isEqualTo(ErrorCode.EXPIRED_COUPON.message)
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
        val exception = assertThrows<IllegalArgumentException> { coupon.use(1000L) }
        assertThat(exception.message).isEqualTo(ErrorCode.USED_COUPON.message)
    }
}