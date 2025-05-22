package kr.hhplus.be.server.domain.order

import kr.hhplus.be.server.support.ErrorCode.ORDER_NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class OrderService(
    private val orderRepository: OrderRepository,
) {
    @Transactional
    fun order(cmd: OrderCommand.Order): OrderInfo.OrderInfo {
        val totalAmount = cmd.price * cmd.quantity
        val order = with(cmd) {
            Order(
                orderId = orderId,
                userId = userId,
                productId = productId,
                quantity = quantity,
                totalAmount = totalAmount,
                paymentAmount = totalAmount,
                createMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
        }
        orderRepository.save(order)
        return OrderInfo.OrderInfo.of(order)
    }

    @Transactional
    fun status(cmd: OrderCommand.Status) {
        orderRepository.findByOrderId(cmd.orderId)?.let { order ->
            orderRepository.save(with(order) {
                Order(
                    orderId,
                    userId,
                    productId,
                    quantity,
                    totalAmount,
                    paymentAmount,
                    cmd.status,
                    createMillis
                )
            })
        } ?: throw NoSuchElementException(ORDER_NOT_FOUND.message)
    }
}