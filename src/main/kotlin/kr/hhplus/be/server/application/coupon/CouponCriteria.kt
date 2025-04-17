package kr.hhplus.be.server.application.coupon

import kr.hhplus.be.server.domain.coupon.CouponCommand
import kr.hhplus.be.server.domain.couponevent.CouponEventCommand
import kr.hhplus.be.server.domain.couponevent.CouponEventInfo

class CouponCriteria {
    class Issue(
        val userId: Long,
        val couponEventId: Long,
    ) {
        fun toFind(): CouponEventCommand.Find = CouponEventCommand.Find(couponEventId)
        fun toIssue(): CouponEventCommand.Issue = CouponEventCommand.Issue(couponEventId)
        fun toIssue(info: CouponEventInfo.Issue): CouponCommand.Issue =
            with(info) { CouponCommand.Issue(userId, type, value, expiryMillis) }
    }
}