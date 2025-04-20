package kr.hhplus.be.server.infrastructure.payment

import jakarta.persistence.*
import kr.hhplus.be.server.domain.payment.Payment

@Entity
@Table(name = "payments")
class PaymentEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val paymentId: Long = 0L,
    @Column(nullable = false)
    val orderId: Long,
    @Column(nullable = false)
    val amount: Long,
    @Column(nullable = false)
    val createMillis: Long,
) {
    companion object {
        fun of(payment: Payment): PaymentEntity =
            with(payment) { PaymentEntity(paymentId, orderId, amount, createMillis) }
    }

    fun to(): Payment = Payment(paymentId, orderId, amount, createMillis)
}