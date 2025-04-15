package kr.hhplus.be.server.interfaces.order

import kr.hhplus.be.server.domain.order.OrderCommand

class OrderRequest {
    data class Order(
        val productId: Long,
        val price: Long,
        val quantity: Int,
    ) {
        fun to(): OrderCommand.Order = OrderCommand.Order(productId, price, quantity)
    }
}