package kr.hhplus.be.server.domain.coupon

class CouponDomainEvent {
    data class CouponUsed(
        val orderId: Long,
        val userId: Long,
        val couponId: Long?,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
        val paymentAmount: Long,
    )

    data class CouponUseFailed(val orderId: Long, val message: String?)
    data class CouponRestored(val orderId: Long)
}