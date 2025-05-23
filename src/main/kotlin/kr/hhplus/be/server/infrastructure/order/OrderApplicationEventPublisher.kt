package kr.hhplus.be.server.infrastructure.order

import kr.hhplus.be.server.domain.order.OrderEvent
import kr.hhplus.be.server.domain.order.OrderEventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class OrderApplicationEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher,
) : OrderEventPublisher {
    override fun publish(event: OrderEvent.OrderCreated) {
        applicationEventPublisher.publishEvent(event)
    }

    override fun publish(event: OrderEvent.OrderCanceled) {
        applicationEventPublisher.publishEvent(event)
    }
}