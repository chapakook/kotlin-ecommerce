package kr.hhplus.be.server.infrastructure.order

import kr.hhplus.be.server.domain.order.Order
import kr.hhplus.be.server.domain.order.OrderRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class OrderRepositoryImpl(
    private val jpaOrderRepository: JpaOrderRepository,
) : OrderRepository {
    @Transactional
    override fun findByOrderId(orderId: Long): Order? = jpaOrderRepository.findByOrderId(orderId)

    @Transactional
    override fun save(order: Order): Order = jpaOrderRepository.save(order)
}