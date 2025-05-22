package kr.hhplus.be.server.domain.order

import kr.hhplus.be.server.domain.coupon.CouponDomainEvent
import kr.hhplus.be.server.domain.stock.StockEvent
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component
import org.springframework.transaction.event.TransactionPhase
import org.springframework.transaction.event.TransactionalEventListener

@Component
class OrderEventListener(
    private val orderService: OrderService,
    private val orderEventPublisher: OrderEventPublisher
) {
    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handler(event: CouponDomainEvent.CouponUseFailed) {
        orderService.status(OrderCommand.Status(event.orderId, OrderStatus.Cancel))
        orderEventPublisher.publish(OrderEvent.OrderCanceled(event.orderId))
    }

    @Async
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    fun handler(event: StockEvent.StockDeducted) {
        orderService.status(OrderCommand.Status(event.orderId, OrderStatus.Completed))
    }
}