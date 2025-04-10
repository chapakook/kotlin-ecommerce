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
    inner class Find {
        @Test
        fun `happy - userId가 양수 일대 정상적으로 찾아온다`() {
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
        fun `happy - userId 양수, amount 양수 이요 요청시 정상적으로 포인트 충전을 한다`() {
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
        fun `happy - 포인트가 있을때 사용한다`() {
            // given
            val useCmd = PointCommand.Use(1L,100L,null)
            val base = PointInfo.Point(1L,1L,100L)
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.use(useCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
        @Test
        fun `happy - 포인트0 일때 주문금액이 0 이면 사용한다`() {
            // given
            val useCmd = PointCommand.Use(1L,0L,null)
            val base = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.use(useCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
        @Test
        fun `happy - 포인트가 사용금액보다 많을때 사용한다`() {
            // given
            val useCmd = PointCommand.Use(1L,100L, null)
            val base = PointInfo.Point(1L,1L,100L)
            val fake = PointInfo.Point(1L,1L,0L)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.updatePointById(any(),any()) } returns fake
            // when
            val result = pointService.use(useCmd)
            // then
            assertThat(result).isEqualTo(fake)
        }
        @Test
        fun `bad - userId 양수, amount 양수 이요 요청시 정상적인 포인트 사용을 한다`() {
            // given
            val useCmd = PointCommand.Use(1L,200L,null)
            val base = PointInfo.Point(1L,1L,100L)
            every { pointRepository.findPointById(any()) } returns base
            // when & then
            assertThatThrownBy { pointService.use(useCmd) }
                .isInstanceOf(IllegalArgumentException::class.java)
        }
    }
}