package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.order.OrderCommand
import kr.hhplus.be.server.domain.product.ProductCommand
import kr.hhplus.be.server.domain.product.ProductInfo

class OrderCriteria {
    data class Order(
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val couponId: Long?,
    ) {
        fun toProductCmd(): ProductCommand.Find = ProductCommand.Find(productId)
        fun toOrderCmd(info: ProductInfo.ProductInfo): OrderCommand.Order = OrderCommand.Order(
            userId = userId,
            productId = productId,
            name = info.name,
            price = info.price,
            quantity = quantity,
        )
    }
}