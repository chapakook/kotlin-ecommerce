package kr.hhplus.be.server.domain.order

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class OrderEventListener(
    private val externalNotify: ExternalNotify
) {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handleNotify(event: OrderEvent.OrderCompleted) {
        externalNotify.notify(with(event) {
            ExternalNotifyCommand.Notify(
                orderId,
                userId,
                productId,
                quantity,
                totalAmount
            )
        })
    }
}