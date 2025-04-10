package kr.hhplus.be.server.domain.order

import java.time.LocalDateTime

class OrderInfo {
    data class Order(
        val orderId: Long,
        val productId: Long,
        val quantity: Int,
        val totalPrice: Long,
        val createdAt: LocalDateTime
    )
}