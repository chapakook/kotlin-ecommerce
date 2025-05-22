package kr.hhplus.be.server.domain.order

interface OrderRepository {
    fun findByOrderId(orderId: Long): Order?
    fun save(order: Order): Order
}