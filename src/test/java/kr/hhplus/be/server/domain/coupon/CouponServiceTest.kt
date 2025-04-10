package kr.hhplus.be.server.domain.coupon

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.mockito.ArgumentMatchers.any
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
        fun `happy - 쿠폰을 정상 사용 성공`() {
            // given
            val userId = 1L
            val code = UUID.randomUUID().toString()
            val useCmd = CouponCommand.Use(userId, code)
            val base = CouponInfo.UserCoupon(userId, 2L, code, LocalDateTime.now().plusDays(30), false)
            val fake = base.copy(isUsed = true)
            val orderCoupon = CouponInfo.OrderCoupon(1L,2L,3L,100L,LocalDateTime.now())
            every { couponRepository.findCouponByUserIdAndCode(any(),any()) } returns base
            every { couponRepository.insertOrderCoupon() } returns orderCoupon
            every { couponRepository.insertUserCoupon() } returns fake
            // when
            val result = couponService.use(useCmd)
            // then
            assertThat(result)
                .extracting("couponId","code","isUsed")
                .contains(fake.couponId, code, true)
        }
        @Test
        fun `happy - 쿠폰이 없는 경우 null로 성공`(){
            // given
            val userId = 1L
            val code = UUID.randomUUID().toString()
            val useCmd = CouponCommand.Use(userId, code)
            every { couponRepository.findCouponByUserIdAndCode(any(),any()) } returns null
            // when
            val result = couponService.use(useCmd)
            // then
            assertThat(result).isNull()
        }
        @Test
        fun `happy - 사용된 쿠폰은 null로 성공`(){
            // given
            val userId = 1L
            val code = UUID.randomUUID().toString()
            val useCmd = CouponCommand.Use(userId, code)
            val base = CouponInfo.UserCoupon(userId, 2L, code, LocalDateTime.now().plusDays(30), true)
            every { couponRepository.findCouponByUserIdAndCode(any(),any()) } returns base
            // when
            val result = couponService.use(useCmd)
            // then
            assertThat(result).isNull()
        }
        @Test
        fun `happy - 만료된 쿠폰은 null로 성공`(){
            // given
            val userId = 1L
            val code = UUID.randomUUID().toString()
            val useCmd = CouponCommand.Use(userId, code)
            val base = CouponInfo.UserCoupon(userId, 2L, code, LocalDateTime.now().plusDays(-10), false)
            every { couponRepository.findCouponByUserIdAndCode(any(),any()) } returns base
            // when
            val result = couponService.use(useCmd)
            // then
            assertThat(result).isNull()
        }
        @Test
        fun `happy - 당일까지 쿠폰은 사용 성공`(){
            // given
            val userId = 1L
            val code = UUID.randomUUID().toString()
            val useCmd = CouponCommand.Use(userId, code)
            val base = CouponInfo.UserCoupon(userId, 2L, code, LocalDateTime.now(), false)
            val fake = base.copy(isUsed = true)
            val orderCoupon = CouponInfo.OrderCoupon(1L,2L,3L,100L,LocalDateTime.now())
            every { couponRepository.findCouponByUserIdAndCode(any(),any()) } returns base
            every { couponRepository.insertOrderCoupon() } returns orderCoupon
            every { couponRepository.insertUserCoupon() } returns fake
            // when
            val result = couponService.use(useCmd)
            // then
            assertThat(result)
                .extracting("couponId","code","isUsed")
                .contains(fake.couponId, code, true)
        }
    }

    @Nested
    inner class Issued {
        @Test
        fun `happy - 쿠폰 제한이 초과하지 않아고 발급받지않은 쿠폰은 발급에 성공`(){
            // given
            val userId = 1L
            val couponId = 2L
            val cmd = CouponCommand.Issue(userId, couponId)
            val limit = 100
            val current = 10
            val coupon = CouponInfo.Coupon(couponId,limit,current)
            val issuedCoupon = CouponInfo.UserCoupon(userId, couponId, UUID.randomUUID().toString(), LocalDateTime.now(), false)
            every { couponRepository.findCouponByCouponId(any()) } returns coupon
            every { couponRepository.findCouponByCouponIdAndUserId(any(),any()) } returns null
            every { couponRepository.updateCoupon() } returns coupon.copy(issuedCount = current+1)
            every { couponRepository.insertUserCoupon() } returns issuedCoupon
            // when
            val result = couponService.issue(cmd)
            // then
            assertThat(result).isNotNull
        }
        @Test
        fun `happy - 쿠폰 발급 제한이 같으면 발급받지 못함`(){
            // given
            val userId = 1L
            val couponId = 2L
            val cmd = CouponCommand.Issue(userId, couponId)
            val limit = 100
            val current = 100
            val coupon = CouponInfo.Coupon(couponId,limit,current)
            every { couponRepository.findCouponByCouponId(any()) } returns coupon
            // when
            val result = couponService.issue(cmd)
            // then
            assertThat(result).isNull()
        }
        @Test
        fun `happy - 쿠폰을 이미 발급받았으면 발급받지 못함`(){
            // given
            val userId = 1L
            val couponId = 2L
            val cmd = CouponCommand.Issue(userId, couponId)
            val limit = 100
            val current = 10
            val coupon = CouponInfo.Coupon(couponId,limit,current)
            val issuedCoupon = CouponInfo.UserCoupon(userId, couponId, UUID.randomUUID().toString(), LocalDateTime.now(), false)
            every { couponRepository.findCouponByCouponId(any()) } returns coupon
            every { couponRepository.findCouponByCouponIdAndUserId(any(),any()) } returns issuedCoupon
            // when
            val result = couponService.issue(cmd)
            // then
            assertThat(result).isNull()
        }
    }
}