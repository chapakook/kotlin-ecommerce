package kr.hhplus.be.server.domain.couponevent

class CouponEventEvent {
    data class CouponIssueRequest(val couponEventId: Long, val userId: Long)
}