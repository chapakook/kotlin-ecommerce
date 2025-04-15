package kr.hhplus.be.server.domain.point

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.random.Random

class PointServiceTest {
    private lateinit var pointService: PointService
    private lateinit var pointRepository: PointRepository

    @BeforeEach
    fun setUp() {
        pointRepository = mockk()
        pointService = PointService(pointRepository)
    }

    private fun createPoint(
        pointId: Long,
        userId: Long,
        balance: Long = Random.nextLong(100L, Long.MAX_VALUE),
    ): Point =
        Point(
            pointId,
            userId,
            balance,
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
        )

    @Nested
    inner class Find {
        @Test
        fun `happy - userId가 양수 일대 성공`() {
            // given
            val findCmd = PointCommand.Find(1L)
            val point = createPoint(2L, findCmd.userId)
            every { pointRepository.findPointById(any()) } returns point
            // when
            val result = pointService.find(findCmd)
            // then
            with(point) {
                assertThat(result)
                    .extracting("pointId", "userId", "balance")
                    .contains(pointId, userId, balance)
            }
        }
    }

    @Nested
    inner class Charge {
        @Test
        fun `happy - userId 양수, amount 양수 이요 요청시 정상적으로 포인트 충전에 성공`() {
            // given
            val chargeCmd = PointCommand.Charge(1L, 100L)
            val base = createPoint(1L, chargeCmd.userId)
            val point = base.copy(balance = base.balance + chargeCmd.amount)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.update(any(), any(), any()) } returns point
            // when
            val result = pointService.charge(chargeCmd)
            // then
            with(point) {
                assertThat(result)
                    .extracting("pointId", "userId", "balance")
                    .contains(pointId, userId, balance)
            }
        }
    }

    @Nested
    inner class Use {
        @Test
        fun `happy - 포인트가 있을때 성공`() {
            // given
            val useCmd = PointCommand.Use(2L, 100L)
            val base = createPoint(1L, useCmd.userId)
            val point = base.copy(balance = base.balance - useCmd.amount)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.update(any(), any(), any()) } returns point
            // when
            val result = pointService.use(useCmd)
            // then
            with(point) {
                assertThat(result)
                    .extracting("pointId", "userId", "balance")
                    .contains(pointId, userId, balance)
            }
        }

        @Test
        fun `happy - 포인트0 일때 주문금액이 0 이면 성공`() {
            // given
            val useCmd = PointCommand.Use(1L, 0L)
            val base = createPoint(1L, useCmd.userId)
            val point = base.copy(balance = base.balance - useCmd.amount)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.update(any(), any(), any()) } returns point
            // when
            val result = pointService.use(useCmd)
            // then
            with(point) {
                assertThat(result)
                    .extracting("pointId", "userId", "balance")
                    .contains(pointId, userId, balance)
            }
        }

        @Test
        fun `happy - 포인트가 사용금액보다 많을때 성공`() {
            // given
            val useCmd = PointCommand.Use(1L, 10L)
            val base = createPoint(1L, useCmd.userId)
            val point = base.copy(balance = base.balance - useCmd.amount)
            every { pointRepository.findPointById(any()) } returns base
            every { pointRepository.update(any(), any(), any()) } returns point
            // when
            val result = pointService.use(useCmd)
            // then
            with(point) {
                assertThat(result)
                    .extracting("pointId", "userId", "balance")
                    .contains(pointId, userId, balance)
            }
        }

        @Test
        fun `bad - 사용금액보다 포인트가 부족하면 IllegalArgumentException을 반환한다`() {
            // given
            val useCmd = PointCommand.Use(1L, 10000L)
            val base = createPoint(1L, useCmd.userId, 100L)
            every { pointRepository.findPointById(any()) } returns base
            // when & then
            assertThatThrownBy { pointService.use(useCmd) }
                .isInstanceOf(IllegalArgumentException::class.java)
                .hasMessage(OUT_OF_POINT.message)
        }
    }
}