package kr.hhplus.be.server.domain.coupon

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import java.time.LocalDateTime
import java.util.*

class CouponServiceTest {
    private lateinit var couponService: CouponService
    private lateinit var couponRepository: CouponRepository

    @BeforeEach
    fun setUp() {
        couponRepository = mockk()
        couponService = CouponService(couponRepository)
    }

    @Nested
    inner class Use{
        @Test
        fun `happy - 쿠폰을 사용한다`() {
            // given
            val userId = 1L
            val code = UUID.randomUUID().toString()
            val useCmd = CouponCommand.Use(userId, code)
            val base = CouponInfo.Coupon(1L, code, LocalDateTime.now().plusDays(30), false)
            val fake = base.copy(isUsed = true)
            every { couponRepository.findCouponByUserIdAndCode(any(),any()) } returns base
            every { couponRepository.update() } returns fake
            // when
            val result = couponService.use(useCmd)
            // then
            assertThat(result)
                .extracting("couponId","code")
                .contains(fake.couponId,fake.code)
        }
    }
}