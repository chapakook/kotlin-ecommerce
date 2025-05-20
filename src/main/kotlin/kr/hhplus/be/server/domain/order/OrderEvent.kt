package kr.hhplus.be.server.domain.order

class OrderEvent {
    data class OrderCompleted(
        val orderId: Long,
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
    )
}