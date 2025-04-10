package kr.hhplus.be.server.domain.order

import org.springframework.stereotype.Service

@Service
class OrderService (
    private val orderRepository: OrderRepository,
) {
    fun order(cmd: OrderCommand.Order): OrderInfo.Order {

        return OrderInfo.Order(1L)
    }
}