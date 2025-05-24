package kr.hhplus.be.server.domain.order

class OrderInfo {
    class OrderInfo(
        val orderId: Long,
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
        val createMillis: Long,
    ) {
        companion object {
            fun of(order: Order): OrderInfo =
                with(order) { OrderInfo(orderId, userId, productId, quantity, totalAmount, createMillis) }
        }
    }
}