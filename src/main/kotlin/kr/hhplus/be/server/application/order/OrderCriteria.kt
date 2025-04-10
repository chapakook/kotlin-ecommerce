package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.coupon.CouponCommand
import kr.hhplus.be.server.domain.order.OrderCommand
import kr.hhplus.be.server.domain.payment.PaymentCommand
import kr.hhplus.be.server.domain.point.PointCommand
import kr.hhplus.be.server.domain.product.ProductCommand

class OrderCriteria {
    data class Order(
        val userId: Long,
        val productId: Long,
        val couponId: Long,
        val price: Long,
        val quantity: Int,
        val code: String?,
    ){
        init {
            require(productId > 0) { "Product id must be positive" }
            require(quantity > 0) { "Quantity must be positive" }
        }
        fun toProductCmdReduce(): ProductCommand.Reduce = ProductCommand.Reduce(productId, quantity)
        fun toCouponCmdUse(): CouponCommand.Use = CouponCommand.Use(userId, code)
        fun toOrderCmdOrder(): OrderCommand.Order = OrderCommand.Order(productId, price, quantity)
        fun toPointCmdUse(): PointCommand.Use = PointCommand.Use(userId, price * quantity, code)
        fun toPaymentCmdPayment(): PaymentCommand.Payment = PaymentCommand.Payment(userId, price * quantity)
    }
}