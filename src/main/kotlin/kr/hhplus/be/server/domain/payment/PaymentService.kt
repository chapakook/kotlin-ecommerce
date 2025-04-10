package kr.hhplus.be.server.domain.payment

import org.springframework.stereotype.Service

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
) {
    fun payment(cmd: PaymentCommand.Payment): PaymentInfo.Payment{
        return paymentRepository.insert()
    }
}