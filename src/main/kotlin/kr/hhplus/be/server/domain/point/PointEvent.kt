package kr.hhplus.be.server.domain.point

class PointEvent {
    data class PointUsed(
        val orderId: Long,
        val userId: Long,
        val couponId: Long?,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
        val paymentAmount: Long,
    )

    data class PointUseFailed(val orderId: Long, val message: String?)
    data class PointRestored(val orderId: Long, val couponId: Long)
}