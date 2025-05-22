package kr.hhplus.be.server.domain.order

interface OrderEventPublisher {
    fun publish(event: OrderEvent.OrderCreated)
    fun publish(event: OrderEvent.OrderCanceled)
}