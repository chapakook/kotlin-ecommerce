package kr.hhplus.be.server.domain.coupon

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class CouponServiceTest {
    private lateinit var couponService: CouponService

    @BeforeEach
    fun setUp() {
        couponService = CouponService()
    }

    @Nested
    inner class GetCoupon{
        @Test
        fun `happy - useCmd 이용 UseCoupon 서비스 요청시 정상동작한다`() {
            // given
            val useCmd = CouponCommand.Use()
            val fakeCoupon = CouponInfo.Coupon(1L)
            // when
            val result = couponService.useCoupon(useCmd)
            // then
            assertThat(result.couponId).isEqualTo(fakeCoupon.couponId)
        }
    }
}