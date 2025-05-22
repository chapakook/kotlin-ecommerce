package kr.hhplus.be.server.domain.payment

import kr.hhplus.be.server.support.ErrorCode.PAYMENT_NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
) {
    @Transactional
    fun pay(cmd: PaymentCommand.Pay): PaymentInfo.Pay {
        val payment = with(cmd) { Payment(orderId = orderId, amount = amount, status = PaymentStatus.Success) }
        paymentRepository.save(payment)
        return PaymentInfo.Pay.of(payment)
    }

    @Transactional
    fun status(cmd: PaymentCommand.Status) {
        paymentRepository.findByOrderId(cmd.orderId)?.let { payment ->
            paymentRepository.save(with(payment) {
                Payment(
                    paymentId,
                    orderId,
                    amount,
                    cmd.status,
                    createMillis
                )
            })
        } ?: throw NoSuchElementException(PAYMENT_NOT_FOUND.message)
    }
}