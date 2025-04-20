package kr.hhplus.be.server.infrastructure.payment

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentJPARepository : JpaRepository<PaymentEntity, Long> {
    fun save(payment: PaymentEntity): PaymentEntity
}