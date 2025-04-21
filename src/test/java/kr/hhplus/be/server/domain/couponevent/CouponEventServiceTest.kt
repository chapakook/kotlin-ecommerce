package kr.hhplus.be.server.domain.couponevent

import io.mockk.every
import io.mockk.mockk
import kr.hhplus.be.server.domain.coupon.CouponType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class CouponEventServiceTest {
    private val couponEventRepository = mockk<CouponEventRepository>()
    private val couponEventService = CouponEventService(couponEventRepository)

    @Nested
    inner class Find {
        @Test
        fun `happy - 쿠폰이벤트가 조회된다`() {
            // given
            val couponEventId = 1L
            val cmd = CouponEventCommand.Find(couponEventId)
            val event = CouponEvent(
                couponEventId,
                100,
                10,
                CouponType.FIXED,
                10L,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            every { couponEventRepository.findByCouponEventId(any()) } returns event
            // when
            val result = couponEventService.find(cmd)
            // then
            with(event) {
                assertThat(result.couponEventId).isEqualTo(couponEventId)
                assertThat(result.maxCount).isEqualTo(maxCount)
                assertThat(result.currentCount).isEqualTo(currentCount)
                assertThat(result.type).isEqualTo(type)
                assertThat(result.value).isEqualTo(value)
                assertThat(result.expiryMillis).isEqualTo(expiryMillis)
            }
        }
    }

    @Nested
    inner class Issue {
        @Test
        fun `happy - 쿠폰이벤트가 발급된다`() {
            // given
            val couponEventId = 2L
            val cmd = CouponEventCommand.Issue(couponEventId)
            val event = CouponEvent(
                couponEventId,
                100,
                10,
                CouponType.FIXED,
                10,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            every { couponEventRepository.findByCouponEventId(any()) } returns event
            // when
            val result = couponEventService.issue(cmd)
            // then
            with(event) {
                assertThat(result.couponEventId).isEqualTo(couponEventId)
                assertThat(result.maxCount).isEqualTo(maxCount)
                assertThat(result.currentCount).isEqualTo(currentCount)
                assertThat(result.type).isEqualTo(type)
                assertThat(result.value).isEqualTo(value)
                assertThat(result.expiryMillis).isEqualTo(expiryMillis)
            }
        }
    }
}