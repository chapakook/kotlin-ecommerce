package kr.hhplus.be.server.domain.couponevent

class CouponEventCommand {
    class Find(val couponEventId: Long)
    class Issue(val couponEventId: Long)
    class Enqueue(val couponEventId: Long, val userId: Long)
    class Dequeue(val couponEventId: Long, val count: Int)
    class Remove(val couponEventId: Long, val userId: Long)
    class HasIssued(val couponEventId: Long, val userId: Long)
    class SetCouponEvent(val couponEventId: Long, val userId: Long)
}