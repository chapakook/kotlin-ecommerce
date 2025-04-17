package kr.hhplus.be.server.domain.order

import jakarta.persistence.*
import java.time.LocalDateTime
import java.time.ZoneOffset

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long = 0L,
    val userId: Long,
    val productId: Long,
    val quantity: Int,
    val totalAmount: Long,
    val paymentAmount: Long,
    val createMillis: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
) {
}