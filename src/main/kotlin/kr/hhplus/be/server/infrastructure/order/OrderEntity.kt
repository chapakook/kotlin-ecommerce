package kr.hhplus.be.server.infrastructure.order

import jakarta.persistence.*
import kr.hhplus.be.server.domain.order.Order

@Entity
@Table(name = "orders")
class OrderEntity(
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
    val createMillis: Long,
) {
    companion object {
        fun of(order: Order): OrderEntity =
            with(order) { OrderEntity(orderId, userId, productId, quantity, totalAmount, paymentAmount, createMillis) }
    }

    fun to(): Order = Order(orderId, userId, productId, quantity, totalAmount, paymentAmount, createMillis)
}