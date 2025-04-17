package kr.hhplus.be.server.infrastructure.couponevent

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.coupon.CouponType
import kr.hhplus.be.server.domain.couponevent.CouponEvent
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CouponEventRepositoryImplTest {
    private val couponEventJPARepository = mockk<CouponEventJPARepository>()
    private val couponEventRepository = CouponEventRepositoryImpl(couponEventJPARepository)

    @Test
    fun `happy - 쿠폰이벤트를 정상적으로 발급함`() {
        // given
        val eventId = 1L
        val expectedCouponEvent = CouponEvent(eventId, 100, 20, CouponType.RATE, 10, System.currentTimeMillis())
        every { couponEventJPARepository.findCouponEventByCouponEventId(eventId) } returns expectedCouponEvent
        // when
        val result = couponEventRepository.findCouponEventByCouponEventId(eventId)
        // then
        assertThat(result).isEqualTo(expectedCouponEvent)
        verify(exactly = 1) { couponEventJPARepository.findCouponEventByCouponEventId(eventId) }
    }

    @Test
    fun `happy - 쿠폰이벤트가 없는경우 null을 반환`() {
        // given
        val eventId = 1L
        every { couponEventJPARepository.findCouponEventByCouponEventId(eventId) } returns null
        // when
        val result = couponEventRepository.findCouponEventByCouponEventId(eventId)
        // then
        assertThat(result).isNull()
        verify(exactly = 1) { couponEventJPARepository.findCouponEventByCouponEventId(eventId) }
    }

    @Test
    fun `happy - 저장하면 쿠폰 이벤트가 반환`() {
        // given
        val couponEvent = CouponEvent(1L, 100, 20, CouponType.RATE, 10, System.currentTimeMillis())
        every { couponEventJPARepository.save(couponEvent) } returns couponEvent
        // when
        val result = couponEventRepository.save(couponEvent)
        // then
        assertThat(result).isEqualTo(couponEvent)
        verify(exactly = 1) { couponEventJPARepository.save(couponEvent) }
    }
}