package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.order.OrderInfo

class OrderResult {
    data class Order(
        val orderId: Long,
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
        val createMillis: Long,
    ) {
        companion object {
            fun of(order: OrderInfo.OrderInfo): Order =
                with(order) { Order(orderId, userId, productId, quantity, totalAmount, createMillis) }
        }
    }
}