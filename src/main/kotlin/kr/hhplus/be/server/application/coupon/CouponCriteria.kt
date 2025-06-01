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

    class Enqueue(
        val couponEventId: Long,
        val userId: Long,
    ) {
        fun to(): CouponEventCommand.Enqueue = CouponEventCommand.Enqueue(couponEventId, userId)
    }

    class Send(
        val couponEventId: Long,
        val userId: Long,
    ) {
        fun to(): CouponEventCommand.Send = CouponEventCommand.Send(couponEventId, userId)
    }

    class Process(
        val couponEventId: Long,
        val count: Int
    ) {
        fun to(): CouponEventCommand.Dequeue = CouponEventCommand.Dequeue(couponEventId, count)
        fun toIssue() = CouponEventCommand.Issue(couponEventId)
        fun toHasIssued(userId: Long): CouponEventCommand.HasIssued =
            CouponEventCommand.HasIssued(couponEventId, userId)

        fun toRemove(userId: Long): CouponEventCommand.Remove = CouponEventCommand.Remove(couponEventId, userId)
        fun toSetCouponEvent(userId: Long): CouponEventCommand.SetCouponEvent =
            CouponEventCommand.SetCouponEvent(couponEventId, userId)
    }
}