package kr.hhplus.be.server.domain.payment

class PaymentInfo {
    data class Payment(
        val orderId: Long,
        val paymentId: Long,
        val totalAmount: Long,
        val totalDiscount: Long,
    )
}