package kr.hhplus.be.server.domain.stock

interface StockEventPublisher {
    fun publish(event: StockEvent.StockDeducted)
    fun publish(event: StockEvent.StockDeductFailed)
}