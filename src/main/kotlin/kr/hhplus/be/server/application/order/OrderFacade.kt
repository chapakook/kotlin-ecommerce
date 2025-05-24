package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.coupon.CouponService
import kr.hhplus.be.server.domain.order.OrderEvent
import kr.hhplus.be.server.domain.order.OrderEventPublisher
import kr.hhplus.be.server.domain.order.OrderService
import kr.hhplus.be.server.domain.payment.PaymentService
import kr.hhplus.be.server.domain.point.PointService
import kr.hhplus.be.server.domain.product.ProductService
import kr.hhplus.be.server.domain.stock.StockService
import kr.hhplus.be.server.support.annotation.IncreasesProductRanking
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderFacade(
    private val productService: ProductService,
    private val stockService: StockService,
    private val couponService: CouponService,
    private val orderService: OrderService,
    private val pointService: PointService,
    private val paymentService: PaymentService,
    private val orderEventPublisher: OrderEventPublisher,
) {
    @Transactional
    @IncreasesProductRanking
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