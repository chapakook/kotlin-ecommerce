package kr.hhplus.be.server.domain.couponevent

import kr.hhplus.be.server.domain.coupon.CouponType
import kr.hhplus.be.server.support.ErrorCode.MAX_ISSUED_COUPON
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class CouponEventTest {

    @Test
    fun `happy - 쿠폰이벤트를 발급할 수 있다`() {
        // given
        var current = 10
        val couponEvent = CouponEvent(
            1L,
            100,
            current,
            CouponType.FIXED,
            500L,
            LocalDateTime.now().plusDays(10).toInstant(ZoneOffset.UTC).toEpochMilli()
        )
        current++
        // when
        couponEvent.issue()
        // then
        assertThat(couponEvent.currentCount).isEqualTo(current)
    }

    @Test
    fun `bad - maxCount가 되면 발급할 수 없다`() {
        // given
        var current = 99
        val couponEvent = CouponEvent(
            1L,
            100,
            current,
            CouponType.FIXED,
            500L,
            LocalDateTime.now().plusDays(10).toInstant(ZoneOffset.UTC).toEpochMilli()
        )
        current++
        couponEvent.issue()
        // when & then
        assertThat(couponEvent.currentCount).isEqualTo(current)
        assertThatThrownBy { couponEvent.issue() }
            .isInstanceOf(IllegalArgumentException::class.java)
            .hasMessageContainingAll(MAX_ISSUED_COUPON.message)
    }
}