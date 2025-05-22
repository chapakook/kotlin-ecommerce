package kr.hhplus.be.server.domain.order

class ExternalNotifyCommand {
    class Notify(
        val orderId: Long,
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
    )
}