package kr.hhplus.be.server.order


enum class OrderStatus {
    PENDING,
    COMPLETE
}

data class OrderResponse(
    val orderId: Long,
    val userId : Long,
    val totalPrice: Int,
    val orderStatus: OrderStatus,
)
