package kr.hhplus.be.server.infrastructure.payment

import kr.hhplus.be.server.domain.payment.Payment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaPaymentRepository : JpaRepository<Payment, Long> {
    fun save(payment: Payment): Payment
}