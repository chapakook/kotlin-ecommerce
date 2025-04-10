package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.coupon.CouponCommand
import kr.hhplus.be.server.domain.order.OrderCommand
import kr.hhplus.be.server.domain.payment.PaymentCommand
import kr.hhplus.be.server.domain.product.ProductCommand

class OrderCriteria {
    data class Order(
        val productId: Long,
        val couponId: Long,
        val quantity: Int,
    ){
        init {
            require(productId > 0) { "Product id must be positive" }
            require(quantity > 0) { "Quantity must be positive" }
        }
        fun toProductCmdGet(): ProductCommand.Get = ProductCommand.Get(1L)
        fun toCouponCmdUse(): CouponCommand.Use = CouponCommand.Use()
        fun toOrderCmdOrder(): OrderCommand.Order = OrderCommand.Order()
        fun toPaymentCmdPayment(): PaymentCommand.Payment = PaymentCommand.Payment()
    }
}