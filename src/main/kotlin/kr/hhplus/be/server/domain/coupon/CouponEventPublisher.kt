package kr.hhplus.be.server.domain.coupon

interface CouponEventPublisher {
    fun publish(event: CouponDomainEvent.CouponUsed)
    fun publish(event: CouponDomainEvent.CouponUseFailed)
    fun publish(event: CouponDomainEvent.CouponRestored)
}