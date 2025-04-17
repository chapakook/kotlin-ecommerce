package kr.hhplus.be.server.infrastructure.coupon

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.coupon.Coupon
import kr.hhplus.be.server.domain.coupon.CouponType
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class CouponRepositoryImplTest {
    private val couponJPARepository = mockk<CouponJPARepository>()
    private val couponRepository = CouponRepositoryImpl(couponJPARepository)

    @Test
    fun `happy - 쿠폰아이디, 유저이디 요청시 정상 반환한다`() {
        // given
        val userId = 1L
        val couponId = 1L
        val expectedCoupon = Coupon(
            couponId,
            userId,
            CouponType.RATE,
            1000L,
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            true
        )
        every { couponJPARepository.findCouponByUserIdAndCouponId(userId, couponId) } returns expectedCoupon
        // when
        val result = couponRepository.findCouponByUserIdAndCouponId(userId, couponId)
        // then
        assertThat(result).isEqualTo(expectedCoupon)
        verify { couponJPARepository.findCouponByUserIdAndCouponId(userId, couponId) }
    }

    @Test
    fun `happy - 저장에 성공하고 값을 반환한다`() {
        // given
        val userId = 2L
        val couponId = 2L
        val coupon = Coupon(
            couponId,
            userId,
            CouponType.RATE,
            1000L,
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            true
        )
        every { couponJPARepository.save(coupon) } returns coupon
        // when
        val savedCoupon = couponRepository.save(coupon)
        // then
        assertThat(savedCoupon).isEqualTo(coupon)
        verify { couponJPARepository.save(coupon) }
    }
}