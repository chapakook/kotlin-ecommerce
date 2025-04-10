package kr.hhplus.be.server.domain.payment

class PaymentInfo {
    data class Payment(
        val paymentId: Long,
        val totalAmount: Long
    )
}