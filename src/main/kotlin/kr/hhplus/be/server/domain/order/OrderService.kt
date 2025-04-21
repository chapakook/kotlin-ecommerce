package kr.hhplus.be.server.domain.order

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    @Transactional
    fun order(cmd: OrderCommand.Order): OrderInfo.OrderInfo {
        val order = with(cmd) { Order(orderId, userId, productId, quantity, totalAmount, paymentAmount) }
        orderRepository.save(order)
        return OrderInfo.OrderInfo.of(order)
    }
}