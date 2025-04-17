package kr.hhplus.be.server.domain.payment

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val paymentId: Long = 0L,
    val orderId: Long,
    val amount: Long,
    val createMillis: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
)