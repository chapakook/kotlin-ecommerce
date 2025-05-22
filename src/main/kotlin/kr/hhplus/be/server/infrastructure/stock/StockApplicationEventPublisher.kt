package kr.hhplus.be.server.infrastructure.stock

import kr.hhplus.be.server.domain.stock.StockEvent
import kr.hhplus.be.server.domain.stock.StockEventPublisher
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

@Component
class StockApplicationEventPublisher(
    private val applicationEventPublisher: ApplicationEventPublisher,
) : StockEventPublisher {
    override fun publish(event: StockEvent.StockDeducted) {
        applicationEventPublisher.publishEvent(event)
    }

    override fun publish(event: StockEvent.StockDeductFailed) {
        applicationEventPublisher.publishEvent(event)
    }
}