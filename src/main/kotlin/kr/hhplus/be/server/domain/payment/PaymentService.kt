package kr.hhplus.be.server.domain.payment

import org.springframework.stereotype.Service

@Service
class PaymentService {
    fun payment(cmd: PaymentCommand.Payment): PaymentInfo.Payment{
        return PaymentInfo.Payment()
    }
}