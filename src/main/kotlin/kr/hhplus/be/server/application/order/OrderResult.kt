package kr.hhplus.be.server.application.order

import kr.hhplus.be.server.domain.order.OrderInfo

class OrderResult {
    data class Order(
        val orderId: Long,
    ){
        init {
        }
    }
    fun ofOrder(order: OrderInfo.Order): Order{
        return Order(order.orderId)
    }
}