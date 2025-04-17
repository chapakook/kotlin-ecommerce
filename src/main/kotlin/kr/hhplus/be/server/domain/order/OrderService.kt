package kr.hhplus.be.server.domain.order

import org.springframework.stereotype.Service

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    fun order(cmd: OrderCommand.Order): OrderInfo.OrderInfo {
        val order = with(cmd) {
            Order(
                userId = userId,
                productId = productId,
                quantity = quantity,
                totalAmount = totalAmount,
                paymentAmount = paymentAmount
            )
        }
        orderRepository.save(order)
        return OrderInfo.OrderInfo.of(order)
    }
}