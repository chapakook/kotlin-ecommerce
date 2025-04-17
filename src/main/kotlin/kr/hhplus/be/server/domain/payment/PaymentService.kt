package kr.hhplus.be.server.domain.payment

import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
) {
    fun pay(cmd: PaymentCommand.Pay): PaymentInfo.Pay {
        val payment = with(cmd) { Payment(orderId = orderId, amount = amount) }
        paymentRepository.save(payment)
        return PaymentInfo.Pay.of(payment)
    }
}