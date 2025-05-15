package kr.hhplus.be.server.infrastructure.order

import kr.hhplus.be.server.domain.order.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaOrderRepository : JpaRepository<Order, Long> {
    fun save(order: Order): Order
}