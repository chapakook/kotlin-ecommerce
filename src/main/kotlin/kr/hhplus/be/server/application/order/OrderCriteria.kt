package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.coupon.CouponCommand
import kr.hhplus.be.server.domain.order.OrderCommand
import kr.hhplus.be.server.domain.point.PointCommand
import kr.hhplus.be.server.domain.product.ProductCommand
import kr.hhplus.be.server.domain.stock.StockCommand

class OrderCriteria {
    data class Order(
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val couponId: Long?,
    ) {
        fun toProductCmd(): ProductCommand.Find = ProductCommand.Find(productId)
        fun toStockCmd(): StockCommand.Deduct = StockCommand.Deduct(productId, quantity)
        fun toCouponCmd(amount: Long): CouponCommand.Use = CouponCommand.Use(userId, couponId, amount)
        fun toPointCmd(amount: Long): PointCommand.Use = PointCommand.Use(userId, amount)
        fun toOrderCmd(totalAmount: Long, paymentAmount: Long): OrderCommand.Order =
            OrderCommand.Order(userId, productId, quantity, totalAmount, paymentAmount)
    }
}