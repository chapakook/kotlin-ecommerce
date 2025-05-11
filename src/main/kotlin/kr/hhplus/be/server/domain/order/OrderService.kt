package kr.hhplus.be.server.domain.order

import org.springframework.cache.annotation.Cacheable
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
        val order = with(cmd) {
            Order(
                orderId,
                userId,
                productId,
                quantity,
                totalAmount,
                paymentAmount,
                LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            )
        }
        orderRepository.save(order)
        return OrderInfo.OrderInfo.of(order)
    }

    @Cacheable(cacheNames = ["rank"], key = "'ALL'")
    fun rank(): List<OrderInfo.OrderProductInfo> = OrderInfo
        .OrderProductInfo
        .ofList(orderRepository.getProductOrderStatsByQuantity())
}