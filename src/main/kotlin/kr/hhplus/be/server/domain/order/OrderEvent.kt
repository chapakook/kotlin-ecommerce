package kr.hhplus.be.server.domain.order

class OrderEvent {
    data class OrderCreated(
        val orderId: Long,
        val userId: Long,
        val couponId: Long?,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
    )

    data class OrderCanceled(val orderId: Long)
}