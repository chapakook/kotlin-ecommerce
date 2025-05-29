package kr.hhplus.be.server.domain.couponevent

interface OutsideCouponEventEventProducer {
    fun send(event: CouponEventEvent.CouponIssueRequest)
}