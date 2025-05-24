package kr.hhplus.be.server.domain.point

import kr.hhplus.be.server.domain.coupon.CouponDomainEvent
import kr.hhplus.be.server.domain.payment.PaymentEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class PointEventListener(
    private val pointService: PointService,
    private val pointEventPublisher: PointEventPublisher,
) {
    @EventListener
    fun handler(event: CouponDomainEvent.CouponUsed) {
        with(event) {
            try {
                pointService.use(PointCommand.Use(userId, paymentAmount))
                pointEventPublisher.publish(
                    PointEvent.PointUsed(
                        orderId,
                        userId,
                        couponId,
                        productId,
                        quantity,
                        totalAmount,
                        paymentAmount,
                    )
                )
            } catch (e: Exception) {
                pointEventPublisher.publish(PointEvent.PointUseFailed(orderId, e.message))
                throw e
            }
        }
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handler(event: PaymentEvent.PaymentCanceled) {
        with(event) {
            pointService.charge(PointCommand.Charge(userId, paymentAmount))
            pointEventPublisher.publish(PointEvent.PointRestored(orderId, couponId))
        }
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handler(event: PaymentEvent.PaymentCreateFailed) {
        with(event) {
            pointService.charge(PointCommand.Charge(userId, paymentAmount))
            couponId?.let { pointEventPublisher.publish(PointEvent.PointRestored(orderId, couponId)) }
        }
    }
}