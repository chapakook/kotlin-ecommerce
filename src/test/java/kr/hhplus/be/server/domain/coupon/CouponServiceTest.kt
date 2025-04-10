package kr.hhplus.be.server.domain.coupon

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import java.time.LocalDateTime
import java.util.*

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
            val fake = CouponInfo.Coupon(1L, UUID.randomUUID().toString(),LocalDateTime.now(), false)
            // when
            val result = couponService.use(useCmd)
            // then
            assertThat(result.couponId).isEqualTo(fake.couponId)
        }
    }
}