package kr.hhplus.be.server.domain.couponevent

class CouponEventCommand {
    class Find(val couponEventId: Long)
    class Issue(val couponEventId: Long)
}