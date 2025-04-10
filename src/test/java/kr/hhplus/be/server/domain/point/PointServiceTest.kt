package kr.hhplus.be.server.domain.point

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import org.mockito.MockitoAnnotations

class PointInfoServiceTest {
    private lateinit var pointService: PointService

    @BeforeEach
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        pointService = PointService()
    }

    @Nested
    inner class GetPointInfo {
        @Test
        fun `happy - getCmd 이용 getPoint 요청시 정상적으로 포인트를 가져온다`() {
            // given
            val getCmd = PointCommand.Get()
            val fakePointInfo = PointInfo(1L,1L,100L)
            // when
            val result = pointService.getPoint(getCmd)
            // then
            assertThat(result).isEqualTo(fakePointInfo)
        }
    }

    @Nested
    inner class ChargePointInfo {
        @Test
        fun `happy - chargeCmd 이용 chargePoint 요청시 정상적으로 포인트 충전을 한다`() {
            // given
            val chargeCmd = PointCommand.Charge()
            val fakePointInfo = PointInfo(1L,1L,100L)
            // when
            val result = pointService.chargePoint(chargeCmd)
            // then
            assertThat(result).isEqualTo(fakePointInfo)
        }
    }

    @Nested
    inner class UsePointInfo {
        @Test
        fun `happy - useCmd 이용 usePoint 요처시 정상적인 포인트 사용을 한다`() {
            // given
            val useCmd = PointCommand.Use()
            val fakePointInfo = PointInfo(1L,1L,100L)
            // when
            val result = pointService.usePoint(useCmd)
            // then
            assertThat(result).isEqualTo(fakePointInfo)
        }
    }
}