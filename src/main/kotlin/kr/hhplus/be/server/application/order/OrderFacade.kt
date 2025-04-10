package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.coupon.CouponService
import kr.hhplus.be.server.domain.order.OrderService
import kr.hhplus.be.server.domain.payment.PaymentService
import kr.hhplus.be.server.domain.point.PointService
import kr.hhplus.be.server.domain.product.ProductService
import org.springframework.stereotype.Service

@Service
class OrderFacade(
    private val productService: ProductService,
    private val couponService: CouponService,
    private val orderService: OrderService,
    private val paymentService: PaymentService
) {
    fun order(cri: OrderCriteria.Order): OrderResult.Order{
        val product = productService.getProduct(cri.toProductCmdGet())
        require( product.stock > 0) { "상품재고가 없습니다" }
        productService.reduceProduct(cri.toProductCmdReduce())
//        val coupon = couponService.useCoupon(cri.toCouponCmdUse()) // 쿠폰의 경우 심화 기능에 있어서 심화에서 구현
        val order = orderService.order(cri.toOrderCmdOrder())
        val payment = paymentService.payment(cri.toPaymentCmdPayment())
        return OrderResult().ofOrder(order)
    }
}