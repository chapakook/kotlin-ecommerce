package kr.hhplus.be.server.domain.stock

import kr.hhplus.be.server.domain.payment.PaymentEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

@Component
class StockEventListener(
    private val stockService: StockService,
    private val stockEventPublisher: StockEventPublisher,
) {
    @EventListener
    fun handler(event: PaymentEvent.PaymentCreated) {
        try {
            stockService.deduct(with(event) { StockCommand.Deduct(productId, quantity) })
            stockEventPublisher.publish(with(event) {
                StockEvent.StockDeducted(
                    orderId,
                    userId,
                    couponId,
                    productId,
                    quantity,
                    totalAmount,
                )
            })
        } catch (e: Exception) {
            stockEventPublisher.publish(with(event) {
                StockEvent.StockDeductFailed(
                    orderId,
                    userId,
                    couponId,
                    productId,
                    quantity,
                    totalAmount,
                    e.message
                )
            })
            throw e
        }

    }
}