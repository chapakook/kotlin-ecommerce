package kr.hhplus.be.server.domain.payment

interface PaymentRepository {
    fun findByOrderId(orderId: Long): Payment?
    fun save(payment: Payment): Payment
}