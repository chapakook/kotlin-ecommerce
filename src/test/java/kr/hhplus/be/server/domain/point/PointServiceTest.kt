package kr.hhplus.be.server.domain.point

import io.mockk.every
import io.mockk.mockk
import kr.hhplus.be.server.support.ErrorCode.OUT_OF_POINT
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import kotlin.random.Random

class PointServiceTest {
    private val pointRepository = mockk<PointRepository>()
    private val pointService = PointService(pointRepository)

    @Nested
    inner class Find {
        @Test
        fun `happy - userId가 양수 일대 성공`() {
            // given
            val findCmd = PointCommand.Find(1L)
            val baseBalance = Random.nextLong(100L, Long.MAX_VALUE)
            val point = Point.create(2L, findCmd.userId, baseBalance)
            every { pointRepository.findByUserId(any()) } returns point
            // when
            val result = pointService.find(findCmd)
            // then
            with(point) {
                assertThat(result.pointId).isEqualTo(pointId)
                assertThat(result.userId).isEqualTo(userId)
                assertThat(result.balance).isEqualTo(balance)
            }
        }
    }

    @Nested
    inner class Charge {
        @Test
        fun `happy - userId 양수, amount 양수 이요 요청시 정상적으로 포인트 충전에 성공`() {
            // given
            val chargeCmd = PointCommand.Charge(1L, 100L)
            val baseBalance = Random.nextLong(100L, Long.MAX_VALUE)
            val base = Point.create(1L, chargeCmd.userId, baseBalance)
            val point = Point.create(1L, chargeCmd.userId, baseBalance + chargeCmd.amount)
            every { pointRepository.findByUserId(any()) } returns base
            every { pointRepository.save(any()) } returns point
            // when
            val result = pointService.charge(chargeCmd)
            // then
            with(point) {
                assertThat(result.pointId).isEqualTo(pointId)
                assertThat(result.userId).isEqualTo(userId)
                assertThat(result.balance).isEqualTo(balance)
            }
        }
    }

    @Nested
    inner class Use {
        @Test
        fun `happy - 포인트가 있을때 성공`() {
            // given
            val useCmd = PointCommand.Use(2L, 100L)
            val baseBalance = Random.nextLong(100L, Long.MAX_VALUE)
            val base = Point.create(1L, useCmd.userId, baseBalance)
            val point = Point.create(1L, useCmd.userId, baseBalance - useCmd.amount)
            every { pointRepository.findByUserId(any()) } returns base
            every { pointRepository.save(any()) } returns point
            // when
            val result = pointService.use(useCmd)
            // then
            with(point) {
                assertThat(result.pointId).isEqualTo(pointId)
                assertThat(result.userId).isEqualTo(userId)
                assertThat(result.balance).isEqualTo(balance)
            }
        }

        @Test
        fun `happy - 포인트0 일때 주문금액이 0 이면 성공`() {
            // given
            val useCmd = PointCommand.Use(1L, 0L)
            val baseBalance = Random.nextLong(100L, Long.MAX_VALUE)
            val base = Point.create(1L, useCmd.userId, baseBalance)
            val point = Point.create(1L, useCmd.userId, baseBalance - useCmd.amount)
            every { pointRepository.findByUserId(any()) } returns base
            every { pointRepository.save(any()) } returns point
            // when
            val result = pointService.use(useCmd)
            // then
            with(point) {
                assertThat(result.pointId).isEqualTo(pointId)
                assertThat(result.userId).isEqualTo(userId)
                assertThat(result.balance).isEqualTo(balance)
            }
        }

        @Test
        fun `happy - 포인트가 사용금액보다 많을때 성공`() {
            // given
            val useCmd = PointCommand.Use(1L, 10L)
            val baseBalance = Random.nextLong(100L, Long.MAX_VALUE)
            val base = Point.create(1L, useCmd.userId, baseBalance)
            val point = Point.create(1L, useCmd.userId, baseBalance - useCmd.amount)
            every { pointRepository.findByUserId(any()) } returns base
            every { pointRepository.save(any()) } returns point
            // when
            val result = pointService.use(useCmd)
            // then
            with(point) {
                assertThat(result.pointId).isEqualTo(pointId)
                assertThat(result.userId).isEqualTo(userId)
                assertThat(result.balance).isEqualTo(balance)
            }
        }

        @Test
        fun `bad - 사용금액보다 포인트가 부족하면 IllegalArgumentException을 반환한다`() {
            // given
            val useCmd = PointCommand.Use(1L, 10000L)
            val base = Point.create(1L, useCmd.userId, 100L)
            every { pointRepository.findByUserId(any()) } returns base
            // when & then
            assertThatThrownBy { pointService.use(useCmd) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(OUT_OF_POINT.message)
        }
    }
}