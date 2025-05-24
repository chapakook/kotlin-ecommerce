package kr.hhplus.be.server.domain.stock

class StockEvent {
    data class StockDeducted(
        val orderId: Long,
        val userId: Long,
        val couponId: Long?,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
    )

    data class StockDeductFailed(
        val orderId: Long,
        val userId: Long,
        val couponId: Long?,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
        val message: String?
    )
}