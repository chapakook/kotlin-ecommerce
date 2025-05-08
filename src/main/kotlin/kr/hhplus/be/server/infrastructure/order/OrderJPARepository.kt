package kr.hhplus.be.server.infrastructure.order

import kr.hhplus.be.server.domain.order.Order
import kr.hhplus.be.server.domain.order.OrderProduct
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderJPARepository : JpaRepository<Order, Long> {
    fun save(order: Order): Order

    @Query(
        """
        SELECT new kr.hhplus.be.server.domain.order.OrderProduct(o.productId as productId, p.name as productName, SUM(o.quantity) as totalOrder)
        FROM Order o JOIN Product p ON o.productId = p.productId
        GROUP BY o.productId, p.name
        ORDER BY SUM(o.quantity) DESC
        """
    )
    fun getProductOrderStatsByQuantity(): List<OrderProduct>
}