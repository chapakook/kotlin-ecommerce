package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.coupon.CouponCommand
import kr.hhplus.be.server.domain.order.OrderCommand
import kr.hhplus.be.server.domain.point.PointCommand
import kr.hhplus.be.server.domain.product.ProductCommand
import kr.hhplus.be.server.domain.stock.StockCommand
import kr.hhplus.be.server.support.RankingTarget

class OrderCriteria {
    data class Order(
        val userId: Long,
        override val productId: Long,
        override val quantity: Int,
        val couponId: Long?,
    ) : RankingTarget {
        fun toProductCmd(): ProductCommand.Find = ProductCommand.Find(productId)
        fun toStockCmd(): StockCommand.Deduct = StockCommand.Deduct(productId, quantity)
        fun toCouponCmd(amount: Long): CouponCommand.Use = CouponCommand.Use(userId, couponId, amount)
        fun toPointCmd(amount: Long): PointCommand.Use = PointCommand.Use(userId, amount)
        fun toOrderCmd(totalAmount: Long, paymentAmount: Long): OrderCommand.Order =
            OrderCommand.Order(
                userId = userId,
                productId = productId,
                quantity = quantity,
                totalAmount = totalAmount,
                paymentAmount = paymentAmount
            )
    }
}