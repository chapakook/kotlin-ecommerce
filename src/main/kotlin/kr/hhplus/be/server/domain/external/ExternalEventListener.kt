package kr.hhplus.be.server.domain.external

import kr.hhplus.be.server.domain.stock.StockEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class ExternalEventListener(
    private val externalNotify: ExternalNotify
) {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handler(event: StockEvent.StockDeducted) {
        externalNotify.notify(with(event) { ExternalNotifyCommand.Notify(orderId) })
    }
}