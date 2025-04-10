package kr.hhplus.be.server.domain.order

import org.springframework.stereotype.Repository

@Repository
interface OrderRepository {
    fun findOrderById(id: Long): OrderInfo.Order
    fun getNextOrderId(): Long
    fun insert(): OrderInfo.Order
    fun update(): OrderInfo.Order
}