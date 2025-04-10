package kr.hhplus.be.server.domain.point

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested

class PointServiceTest {
    private lateinit var pointService: PointService
    private lateinit var pointRepository: PointRepository

    @BeforeEach
    fun setUp() {
        pointRepository = mockk()
        pointService = PointService(pointRepository)
    }

    @Nested
    inner class GetPoint {
        @Test
        fun `happy - userId 양수 일때 포인트를 가져온다`() {
            // given
            val getCmd = PointCommand.Get(1L)
            val fake = PointInfo.Point(1L,1L,100L)
            every { pointRepository.findPointById(any()) } returns fake
            // when
            val result = pointService.getPoint(getCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }

    }

    @Nested
    inner class ChargePoint {
        @Test
        fun `happy - userId 양수, amount 양수 이요 요청시 정상적으로 포인트 충전을 한다`() {
            // given
            val chargeCmd = PointCommand.Charge(1L,100L)
            val fake = PointInfo.Point(1L,1L,100L)
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.chargePoint(chargeCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
    }

    @Nested
    inner class UsePoint {
        @Test
        fun `happy - 포인트가 있을때 사용한다`() {
            // given
            val useCmd = PointCommand.Use(1L,100L)
            val base = PointInfo.Point(1L,1L,100L)
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.usePoint(useCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
        @Test
        fun `happy - 포인트가 사용금액보다 많을때 사용한다`() {
            // given
            val useCmd = PointCommand.Use(1L,100L)
            val base = PointInfo.Point(1L,1L,100L)
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.usePoint(useCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
        @Test
        fun `bad - 포인트가 0일때 사용하지 못한다`() {
            // given
            val useCmd = PointCommand.Use(1L,100L)
            val base = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            // when & then
            assertThatThrownBy { pointService.usePoint(useCmd) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
        @Test
        fun `bad - userId 양수, amount 양수 이요 요청시 정상적인 포인트 사용을 한다`() {
            // given
            val useCmd = PointCommand.Use(1L,200L)
            val base = PointInfo.Point(1L,1L,100L)
            every { pointRepository.findPointById(any()) } returns base
            // when & then
            assertThatThrownBy { pointService.usePoint(useCmd) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}