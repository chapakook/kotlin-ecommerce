package kr.hhplus.be.server.infrastructure.coupon

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
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
        val entity = CouponEntity(
            couponId,
            userId,
            CouponType.RATE,
            1000L,
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            true
        )
        every { couponJPARepository.findCouponByUserIdAndId(any(), any()) } returns entity
        // when
        val result = couponRepository.findCouponByUserIdAndId(userId, couponId)
        // then
        result?.let {
            assertThat(result.couponId).isEqualTo(entity.couponId)
            assertThat(result.userId).isEqualTo(entity.userId)
            assertThat(result.type).isEqualTo(entity.type)
            assertThat(result.value).isEqualTo(entity.value)
            assertThat(result.expiryMillis).isEqualTo(entity.expiryMillis)
            assertThat(result.isActive).isEqualTo(entity.isActive)
        }
        verify { couponJPARepository.findCouponByUserIdAndId(any(), any()) }
    }

    @Test
    fun `happy - 저장에 성공하고 값을 반환한다`() {
        // given
        val userId = 2L
        val couponId = 2L
        val entity = CouponEntity(
            couponId,
            userId,
            CouponType.RATE,
            1000L,
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            false
        )
        every { couponJPARepository.save(any()) } returns entity
        // when
        val result = couponRepository.save(entity.to())
        // then
        assertThat(result.couponId).isEqualTo(entity.couponId)
        assertThat(result.userId).isEqualTo(entity.userId)
        assertThat(result.type).isEqualTo(entity.type)
        assertThat(result.value).isEqualTo(entity.value)
        assertThat(result.expiryMillis).isEqualTo(entity.expiryMillis)
        assertThat(result.isActive).isEqualTo(entity.isActive)
        verify(exactly = 1) { couponJPARepository.save(any()) }
    }
}