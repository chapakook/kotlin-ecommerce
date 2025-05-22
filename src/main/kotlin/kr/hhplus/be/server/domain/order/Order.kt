package kr.hhplus.be.server.domain.order

import jakarta.persistence.*

enum class OrderStatus {
    Pending, Cancel, Completed
}

@Entity
@Table(name = "orders")
class Order(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val orderId: Long = 0L,
    @Column(nullable = false)
    val userId: Long,
    @Column(nullable = false)
    val productId: Long,
    @Column(nullable = false)
    val quantity: Int,
    @Column(nullable = false)
    val totalAmount: Long,
    @Column(nullable = false)
    val paymentAmount: Long,
    @Column(nullable = false)
    val status: OrderStatus = OrderStatus.Pending,
    @Column(nullable = false)
    val createMillis: Long,
)