package kr.hhplus.be.server.infrastructure.payment

import kr.hhplus.be.server.domain.payment.PaymentEvent
import kr.hhplus.be.server.domain.payment.PaymentEventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class PaymentApplicationEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher,
) : PaymentEventPublisher {
    override fun publish(event: PaymentEvent.PaymentCanceled) {
        applicationEventPublisher.publishEvent(event)
    }

    override fun publish(event: PaymentEvent.PaymentCreated) {
        applicationEventPublisher.publishEvent(event)
    }

    override fun publish(event: PaymentEvent.PaymentCreateFailed) {
        applicationEventPublisher.publishEvent(event)
    }
}