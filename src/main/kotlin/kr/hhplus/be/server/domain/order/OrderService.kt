package kr.hhplus.be.server.domain.order

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService (
    private val orderRepository: OrderRepository,
) {
    fun order(cmd: OrderCommand.Order): OrderInfo.Order {
        val orderId = orderRepository.getNextOrderId()
        return with(cmd){
            OrderInfo.Order(orderId, productId, quantity, quantity * price, LocalDateTime.now())
        }
    }
}