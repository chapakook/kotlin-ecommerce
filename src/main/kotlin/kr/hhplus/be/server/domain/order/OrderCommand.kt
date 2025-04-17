package kr.hhplus.be.server.domain.order

class OrderCommand {
    class Order(
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
        val paymentAmount: Long,
    )
}