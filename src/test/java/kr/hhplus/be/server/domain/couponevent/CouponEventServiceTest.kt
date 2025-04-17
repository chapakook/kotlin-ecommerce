package kr.hhplus.be.server.domain.couponevent

import io.mockk.every
import io.mockk.mockk
import kr.hhplus.be.server.domain.coupon.CouponType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.time.LocalDateTime
import java.time.ZoneOffset

class CouponEventServiceTest {
    private lateinit var couponEventRepository: CouponEventRepository
    private lateinit var couponEventService: CouponEventService

    @BeforeEach
    fun setup() {
        couponEventRepository = mockk()
        couponEventService = CouponEventService(couponEventRepository)
    }

    @Nested
    inner class Find {
        @Test
        fun `happy - 쿠폰이벤트가 조회된다`() {
            // given
            val couponEventId = 1L
            val cmd = CouponEventCommand.Find(couponEventId)
            val couponEvent = CouponEvent(
                couponEventId,
                100,
                10,
                CouponType.FIXED,
                10L,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            every { couponEventRepository.findCouponEventByCouponEventId(any()) } returns couponEvent
            // when
            val result = couponEventService.find(cmd)
            // then
            with(couponEvent) {
                assertThat(result)
                    .extracting("couponEventId", "maxCount", "currentCount", "type", "value", "expiryMillis")
                    .containsExactly(couponEventId, maxCount, currentCount, type, value, expiryMillis)
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
            val couponEvent = CouponEvent(
                couponEventId,
                100,
                10,
                CouponType.FIXED,
                10,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
            every { couponEventRepository.findCouponEventByCouponEventId(any()) } returns couponEvent
            // when
            val result = couponEventService.issue(cmd)
            // then
            with(couponEvent) {
                assertThat(result)
                    .extracting("couponEventId", "maxCount", "currentCount", "type", "value", "expiryMillis")
                    .containsExactly(couponEventId, maxCount, currentCount++, type, value, expiryMillis)
            }
        }
    }
}