package kr.hhplus.be.server.application.coupon

import kr.hhplus.be.server.domain.coupon.CouponService
import kr.hhplus.be.server.domain.couponevent.CouponEventService
import org.springframework.stereotype.Service

@Service
class CouponFacade(
    private val couponService: CouponService,
    private val couponEventService: CouponEventService,
) {
    fun issue(cri: CouponCriteria.Issue): CouponResult.Issue =
        CouponResult.Issue.of(couponService.issue(cri.toIssue(couponEventService.issue(cri.toIssue()))))
}