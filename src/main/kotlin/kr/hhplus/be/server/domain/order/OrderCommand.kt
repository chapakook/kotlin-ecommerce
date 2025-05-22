package kr.hhplus.be.server.domain.order

class OrderCommand {
    class Order(
        val orderId: Long = 0L,
        val userId: Long,
        val productId: Long,
        val name: String,
        val price: Long,
        val quantity: Int,
    )

    class Status(val orderId: Long, val status: OrderStatus)
}