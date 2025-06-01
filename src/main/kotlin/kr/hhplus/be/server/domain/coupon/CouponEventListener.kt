package kr.hhplus.be.server.domain.coupon

import kr.hhplus.be.server.domain.order.OrderEvent
import kr.hhplus.be.server.domain.point.PointEvent
import org.springframework.context.event.EventListener
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class CouponEventListener(
    private val couponService: CouponService,
    private val couponEventPublisher: CouponEventPublisher
) {
    @Transactional
    @EventListener
    fun handler(event: OrderEvent.OrderCreated) {
        try {
            with(event) {
                var paymentAmount = totalAmount
                couponId?.let { couponId ->
                    val discountAmount = couponService.use(CouponCommand.Use(userId, couponId, totalAmount))
                    paymentAmount -= discountAmount
                }
                couponEventPublisher.publish(
                    CouponDomainEvent.CouponUsed(
                        orderId,
                        userId,
                        couponId,
                        productId,
                        quantity,
                        totalAmount,
                        paymentAmount,
                    )
                )
            }
        } catch (e: Exception) {
            couponEventPublisher.publish(CouponDomainEvent.CouponUseFailed(event.orderId, e.message))
            throw e
        }
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handler(event: PointEvent.PointRestored) {
        couponService.restore(CouponCommand.Restore(event.orderId, event.orderId))
        couponEventPublisher.publish(CouponDomainEvent.CouponRestored(event.orderId))
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    fun handler(event: PointEvent.PointUseFailed) {
        couponService.restore(CouponCommand.Restore(event.orderId, event.orderId))
        couponEventPublisher.publish(CouponDomainEvent.CouponRestored(event.orderId))
    }
}