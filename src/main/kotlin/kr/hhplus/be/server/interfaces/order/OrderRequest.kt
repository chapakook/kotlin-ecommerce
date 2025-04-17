package kr.hhplus.be.server.interfaces.order

import kr.hhplus.be.server.application.order.OrderCriteria

class OrderRequest {
    data class Order(
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val couponId: Long?,
    ) {
        fun to(): OrderCriteria.Order = OrderCriteria.Order(userId, productId, quantity, couponId)
    }
}