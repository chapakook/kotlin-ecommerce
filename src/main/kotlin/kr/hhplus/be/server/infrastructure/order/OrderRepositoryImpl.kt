package kr.hhplus.be.server.infrastructure.order

import kr.hhplus.be.server.domain.order.Order
import kr.hhplus.be.server.domain.order.OrderRepository
import org.springframework.stereotype.Repository

@Repository
class OrderRepositoryImpl(
    private val orderJPARepository: OrderJPARepository,
) : OrderRepository {
    override fun save(order: Order): Order = orderJPARepository.save(order)
}