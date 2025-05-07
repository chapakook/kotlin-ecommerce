package kr.hhplus.be.server.domain.payment

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
@Table(name = "payments")
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val paymentId: Long = 0L,
    @Column(nullable = false)
    val orderId: Long,
    @Column(nullable = false)
    val amount: Long,
    @Column(nullable = false)
    val createMillis: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
)