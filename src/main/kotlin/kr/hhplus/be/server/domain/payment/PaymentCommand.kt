package kr.hhplus.be.server.domain.payment

class PaymentCommand {
    class Pay(val orderId: Long, val amount: Long)
    class Status(val orderId: Long, val status: PaymentStatus)
}