package kr.hhplus.be.server.domain.payment

class PaymentInfo {
    class Pay(
        val paymentId: Long,
        val orderId: Long,
        val amount: Long,
        val createMillis: Long,
    ) {
        companion object {
            fun of(payment: Payment): Pay = with(payment) { Pay(paymentId, orderId, amount, createMillis) }
        }
    }
}