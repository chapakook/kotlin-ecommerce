package kr.hhplus.be.server.domain.payment

interface PaymentEventPublisher {
    fun publish(event: PaymentEvent.PaymentCreated)
    fun publish(event: PaymentEvent.PaymentCreateFailed)
    fun publish(event: PaymentEvent.PaymentCanceled)
}