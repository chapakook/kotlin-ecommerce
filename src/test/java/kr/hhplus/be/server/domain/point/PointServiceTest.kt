package kr.hhplus.be.server.domain.point

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import java.util.UUID

class PointServiceTest {
    private lateinit var pointService: PointService
    private lateinit var pointRepository: PointRepository

    @BeforeEach
    fun setUp() {
        pointRepository = mockk()
        pointService = PointService(pointRepository)
    }

    @Nested
    inner class Find {
        @Test
        fun `happy - userId가 양수 일대 성공`() {
            // given
            val getCmd = PointCommand.Get(1L)
            val fake = PointInfo.Point(1L,1L,100L)
            every { pointRepository.findPointById(any()) } returns fake
            // when
            val result = pointService.find(getCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
    }

    @Nested
    inner class Charge {
        @Test
        fun `happy - userId 양수, amount 양수 이요 요청시 정상적으로 포인트 충전에 성공`() {
            // given
            val chargeCmd = PointCommand.Charge(1L,100L)
            val fake = PointInfo.Point(1L,1L,100L)
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.charge(chargeCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
    }

    @Nested
    inner class Use {
        @Test
        fun `happy - 포인트가 있을때 성공`() {
            // given
            val userId = 1L
            val pointId = 2L
            val couponId = 3L
            val code =UUID.randomUUID().toString()
            val useCmd = PointCommand.Use(userId,100L,code)
            val base = PointInfo.Point(pointId,userId,100L)
            val coupon = PointInfo.UserCoupon(10L)
            val fake = PointInfo.Point(pointId,userId,0L)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.findUserCoupon(any()) } returns coupon
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.use(useCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
        @Test
        fun `happy - 포인트0 일때 주문금액이 0 이면 성공`() {
            // given
            val useCmd = PointCommand.Use(1L,0L,UUID.randomUUID().toString())
            val base = PointInfo.Point(1L,1L,100L)
            val coupon = PointInfo.UserCoupon(100L)
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findUserCoupon(any()) } returns coupon
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.use(useCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
        @Test
        fun `happy - 포인트가 사용금액보다 많을때 성공`() {
            // given
            val useCmd = PointCommand.Use(1L,100L, UUID.randomUUID().toString())
            val base = PointInfo.Point(1L,1L,100L)
            val coupon = PointInfo.UserCoupon(10L)
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.findUserCoupon(any()) } returns coupon
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.use(useCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
        @Test
        fun `happy - 포인트0 일때 주문금액이 0 쿠폰이 null이여도 성공`() {
            // given
            val useCmd = PointCommand.Use(1L,0L,null)
            val base = PointInfo.Point(1L,1L,100L)
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findUserCoupon(any()) } returns null
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.use(useCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
        @Test
        fun `happy - 포인트보다 할인 금액이 많으면 0 원 결제 성공`() {
            // given
            val useCmd = PointCommand.Use(1L,0L,UUID.randomUUID().toString())
            val base = PointInfo.Point(1L,1L,100L)
            val coupon = PointInfo.UserCoupon(100L)
            val fake = PointInfo.Point(1L,1L,100L)
            every { pointRepository.findUserCoupon(any()) } returns coupon
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.use(useCmd)
            // then
            assertThat(result.balance).isEqualTo(fake.balance)
        }
        @Test
        fun `bad - 사용금액보다 포인트가 부족하면 실패`() {
            // given
            val useCmd = PointCommand.Use(1L,10000L, null)
            val base = PointInfo.Point(1L,1L,100L)
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.findUserCoupon(any()) } returns null
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when & then
            assertThatThrownBy { pointService.use(useCmd) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - 사용금액-할인금액보다 포인트가 부족하면 실패`() {
            // given
            val useCmd = PointCommand.Use(1L,10000L, UUID.randomUUID().toString())
            val base = PointInfo.Point(1L,1L,100L)
            val coupon = PointInfo.UserCoupon(100L)
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.findUserCoupon(any()) } returns coupon
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when & then
            assertThatThrownBy { pointService.use(useCmd) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}