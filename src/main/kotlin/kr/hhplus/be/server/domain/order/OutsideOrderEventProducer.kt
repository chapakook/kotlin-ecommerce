package kr.hhplus.be.server.domain.order

interface OutsideOrderEventProducer {
    fun send(event: OrderEvent.OrderCompleted)
}