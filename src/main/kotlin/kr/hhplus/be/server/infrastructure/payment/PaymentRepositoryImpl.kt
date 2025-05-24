package kr.hhplus.be.server.infrastructure.payment

import kr.hhplus.be.server.domain.payment.Payment
import kr.hhplus.be.server.domain.payment.PaymentRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PaymentRepositoryImpl(
    private val jpaPaymentRepository: JpaPaymentRepository,
) : PaymentRepository {
    @Transactional
    override fun findByOrderId(orderId: Long): Payment? = jpaPaymentRepository.findByOrderId(orderId)

    @Transactional
    override fun save(payment: Payment): Payment = jpaPaymentRepository.save(payment)
}