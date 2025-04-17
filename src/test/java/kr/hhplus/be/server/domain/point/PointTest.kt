package kr.hhplus.be.server.domain.point

import kr.hhplus.be.server.support.ErrorCode
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.LocalDateTime
import java.time.ZoneOffset

class PointTest {

    @Nested
    inner class Charge {
        @Test
        fun `happy - 정상 요금에 대해 충전할 수 있다`() {
            // given
            val balance = 1000L
            val point = Point(
                1L,
                2L,
                balance,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            val amount = 100L
            // when
            point.charge(amount)
            // then
            assertThat(point.balance).isEqualTo(amount + balance)
        }

        @Test
        fun `bad - 이상 요금에 대해서 IllegalArgumentException 반환한다`() {
            // given
            val balance = 1000L
            val point = Point(
                1L,
                2L,
                balance,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            val amount = -100L
            // when & then
            val exception = assertThrows<IllegalArgumentException> { point.charge(amount) }
            assertThat(exception.message).isEqualTo(ErrorCode.AMOUNT_MUST_BE_POSITIVE.message)
        }
    }

    @Nested
    inner class Use {
        @Test
        fun `happy - 잔액이 사용금액보다 많으면 정상 사용할 수 있다`() {
            // given
            val balance = 1000L
            val point = Point(
                1L,
                2L,
                balance,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            val amount = 100L
            // when
            point.use(amount)
            // then
            assertThat(point.balance).isEqualTo(balance - amount)
        }

        @Test
        fun `happy - 사용금액과 잔액이 같아도 정상 사용할 수 있다`() {
            // given
            val balance = 1000L
            val point = Point(
                1L,
                2L,
                balance,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            val amount = 1000L
            // when
            point.use(amount)
            // then
            assertThat(point.balance).isEqualTo(balance - amount)
        }

        @Test
        fun `bad - 사용금액이 잔액보다 많으면 IllegalArgumentException 반환한다`() {
            // given
            val balance = 1000L
            val point = Point(
                1L,
                2L,
                balance,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            val amount = 1200L
            // when & then
            val exception = assertThrows<IllegalArgumentException> { point.use(amount) }
            assertThat(exception.message).isEqualTo(ErrorCode.OUT_OF_POINT.message)
        }
    }
}