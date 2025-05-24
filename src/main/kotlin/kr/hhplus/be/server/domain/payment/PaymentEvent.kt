package kr.hhplus.be.server.domain.payment

class PaymentEvent {
    data class PaymentCreated(
        val orderId: Long,
        val userId: Long,
        val couponId: Long?,
        val productId: Long,
        val quantity: Int,
        val paymentAmount: Long,
        val totalAmount: Long,
    )

    data class PaymentCreateFailed(
        val orderId: Long,
        val userId: Long,
        val couponId: Long?,
        val productId: Long,
        val quantity: Int,
        val paymentAmount: Long,
        val totalAmount: Long,
        val message: String?
    )

    data class PaymentCanceled(val orderId: Long, val userId: Long, val couponId: Long, val paymentAmount: Long)
}