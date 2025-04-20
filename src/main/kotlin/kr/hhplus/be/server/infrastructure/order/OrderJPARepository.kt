package kr.hhplus.be.server.infrastructure.order

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface OrderJPARepository : JpaRepository<OrderEntity, Long> {
    fun save(order: OrderEntity): OrderEntity
}