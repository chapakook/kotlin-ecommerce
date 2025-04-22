package kr.hhplus.be.server.domain.couponevent

import kr.hhplus.be.server.support.ErrorCode.COUPON_EVENT_NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponEventService(
    private val couponEventRepository: CouponEventRepository,
) {
    fun find(cmd: CouponEventCommand.Find): CouponEventInfo.Find =
        couponEventRepository.findCouponEventByCouponEventId(cmd.couponEventId)?.let { couponEvent ->
            CouponEventInfo.Find.of(couponEvent)
        } ?: throw NoSuchElementException(COUPON_EVENT_NOT_FOUND.message)

    @Transactional
    fun issue(cmd: CouponEventCommand.Issue): CouponEventInfo.Issue =
        couponEventRepository.findCouponEventByCouponEventId(cmd.couponEventId)?.let { couponEvent ->
            couponEvent.issue()
            CouponEventInfo.Issue.of(couponEvent)
        } ?: throw NoSuchElementException(COUPON_EVENT_NOT_FOUND.message)
}