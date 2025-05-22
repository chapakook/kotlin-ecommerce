package kr.hhplus.be.server.infrastructure.coupon

import kr.hhplus.be.server.domain.coupon.CouponDomainEvent
import kr.hhplus.be.server.domain.coupon.CouponEventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class CouponApplicationEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher,
) : CouponEventPublisher {
    override fun publish(event: CouponDomainEvent.CouponUsed) {
        applicationEventPublisher.publishEvent(event)
    }

    override fun publish(event: CouponDomainEvent.CouponUseFailed) {
        applicationEventPublisher.publishEvent(event)
    }

    override fun publish(event: CouponDomainEvent.CouponRestored) {
        applicationEventPublisher.publishEvent(event)
    }
}