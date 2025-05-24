package kr.hhplus.be.server.infrastructure.coupon

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.coupon.Coupon
import kr.hhplus.be.server.domain.coupon.CouponType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CouponRepositoryImplTest {
    private val jpaCouponRepository = mockk<JpaCouponRepository>()
    private val couponRepository = CouponRepositoryImpl(jpaCouponRepository)

    @Test
    fun `happy - 쿠폰아이디, 유저이디 요청시 정상 반환한다`() {
        // given
        val userId = 1L
        val couponId = 1L
        val coupon = Coupon(couponId, userId, CouponType.RATE, 1000L, 0, 0, 0, true)
        every { jpaCouponRepository.findByCouponIdAndUserId(any(), any()) } returns coupon
        // when
        val result = couponRepository.findByCouponIdAndUserId(couponId, userId)
        // then
        result?.let {
            assertThat(result.couponId).isEqualTo(coupon.couponId)
            assertThat(result.userId).isEqualTo(coupon.userId)
            assertThat(result.type).isEqualTo(coupon.type)
            assertThat(result.value).isEqualTo(coupon.value)
            assertThat(result.expiryMillis).isEqualTo(coupon.expiryMillis)
            assertThat(result.isActive).isEqualTo(coupon.isActive)
        }
        verify { jpaCouponRepository.findByCouponIdAndUserId(any(), any()) }
    }

    @Test
    fun `happy - 저장에 성공하고 값을 반환한다`() {
        // given
        val userId = 2L
        val couponId = 2L
        val coupon = Coupon(couponId, userId, CouponType.RATE, 1000L, 0, 0, 0, false)
        every { jpaCouponRepository.save(any()) } returns coupon
        // when
        val result = couponRepository.save(coupon)
        // then
        assertThat(result.couponId).isEqualTo(coupon.couponId)
        assertThat(result.userId).isEqualTo(coupon.userId)
        assertThat(result.type).isEqualTo(coupon.type)
        assertThat(result.value).isEqualTo(coupon.value)
        assertThat(result.expiryMillis).isEqualTo(coupon.expiryMillis)
        assertThat(result.isActive).isEqualTo(coupon.isActive)
        verify(exactly = 1) { jpaCouponRepository.save(any()) }
    }
}