package kr.hhplus.be.server.domain.payment

import kr.hhplus.be.server.domain.point.PointEvent
import kr.hhplus.be.server.domain.stock.StockEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class PaymentEventListener(
    private val paymentService: PaymentService,
    private val paymentEventPublisher: PaymentEventPublisher,
) {
    @Transactional
    @EventListener
    fun handler(event: PointEvent.PointUsed) {
        with(event) {
            try {
                paymentService.pay(PaymentCommand.Pay(orderId, paymentAmount))
                paymentEventPublisher.publish(
                    PaymentEvent.PaymentCreated(
                        orderId,
                        userId,
                        couponId,
                        productId,
                        quantity,
                        paymentAmount,
                        totalAmount,
                    )
                )
            } catch (e: Exception) {
                paymentEventPublisher.publish(
                    PaymentEvent.PaymentCreateFailed(
                        orderId,
                        userId,
                        couponId,
                        productId,
                        quantity,
                        paymentAmount,
                        totalAmount,
                        e.message
                    )
                )
                throw e
            }
        }
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handler(event: StockEvent.StockDeductFailed) {
        paymentService.status(with(event) {
            PaymentCommand.Status(orderId, PaymentStatus.Canceled)
        })
    }
}