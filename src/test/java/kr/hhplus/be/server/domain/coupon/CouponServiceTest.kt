package kr.hhplus.be.server.domain.coupon

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class CouponServiceTest {
    private val couponRepository = mockk<CouponRepository>()
    private val couponService = CouponService(couponRepository)

    @Nested
    inner class Find {
        @Test
        fun `happy - 쿠폰 조회에 성공한다`() {
            // given
            val userId = 1L
            val couponId = 2L
            val cmd = CouponCommand.Find(userId, couponId)
            val expiryMillis = LocalDateTime.now().plusDays(10).toInstant(ZoneOffset.UTC).toEpochMilli()
            val createMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            val updateMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            val coupon = Coupon(couponId, userId, CouponType.RATE, 100L, expiryMillis, createMillis, updateMillis, true)
            every { couponRepository.findByCouponIdAndUserId(any(), any()) } returns coupon
            // when
            val result = couponService.find(cmd)
            // then
            with(coupon) {
                assertThat(result.couponId).isEqualTo(coupon.couponId)
                assertThat(result.userId).isEqualTo(coupon.userId)
                assertThat(result.type).isEqualTo(coupon.type)
                assertThat(result.value).isEqualTo(coupon.value)
                assertThat(result.expiryMillis).isEqualTo(coupon.expiryMillis)
                assertThat(result.isActive).isEqualTo(coupon.isActive)
            }
        }
    }
}