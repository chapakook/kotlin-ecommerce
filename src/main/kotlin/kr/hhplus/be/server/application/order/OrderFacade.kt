package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.order.OrderEvent
import kr.hhplus.be.server.domain.order.OrderEventPublisher
import kr.hhplus.be.server.domain.order.OrderService
import kr.hhplus.be.server.domain.product.ProductService
import org.springframework.stereotype.Service

@Service
class OrderFacade(
    private val productService: ProductService,
    private val orderService: OrderService,
    private val orderEventPublisher: OrderEventPublisher,
) {
    fun order(cri: OrderCriteria.Order): OrderResult.Order {
        val order = orderService.order(cri.toOrderCmd(productService.find(cri.toProductCmd())))
        orderEventPublisher.publish(with(order) {
            OrderEvent.OrderCreated(
                orderId,
                userId,
                cri.couponId,
                productId,
                quantity,
                totalAmount
            )
        })
        return OrderResult.Order.of(order)
    }
}