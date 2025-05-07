package kr.hhplus.be.server.application.order

import jakarta.transaction.Transactional
import kr.hhplus.be.server.domain.coupon.CouponService
import kr.hhplus.be.server.domain.order.OrderService
import kr.hhplus.be.server.domain.payment.PaymentCommand
import kr.hhplus.be.server.domain.payment.PaymentService
import kr.hhplus.be.server.domain.point.PointService
import kr.hhplus.be.server.domain.product.ProductService
import kr.hhplus.be.server.domain.stock.StockService
import org.springframework.stereotype.Service

@Service
class OrderFacade(
    private val productService: ProductService,
    private val stockService: StockService,
    private val couponService: CouponService,
    private val orderService: OrderService,
    private val pointService: PointService,
    private val paymentService: PaymentService,
) {
    @Transactional
    fun order(cri: OrderCriteria.Order): OrderResult.Order {
        val product = productService.find(cri.toProductCmd())
        val total = product.price * cri.quantity
        val amount = couponService.use(cri.toCouponCmd(total))
        val order = orderService.order(cri.toOrderCmd(total, amount))
        paymentService.pay(PaymentCommand.Pay(order.orderId, amount))
        pointService.use(cri.toPointCmd(amount))
        stockService.deduct(cri.toStockCmd())
        return OrderResult.Order.of(order)
    }
}