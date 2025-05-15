package kr.hhplus.be.server.infrastructure.product

import kr.hhplus.be.server.domain.product.Product
import kr.hhplus.be.server.domain.product.ProductOrder
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductJPARepository : JpaRepository<Product, Long> {
    fun findByProductId(productId: Long): Product?

    @Query(
        """
        SELECT new kr.hhplus.be.server.domain.product.ProductOrder(o.productId as productId, p.name as productName, SUM(o.quantity) as totalOrder)
        FROM Product p JOIN Order o  ON o.productId = p.productId
        GROUP BY o.productId, p.name
        ORDER BY SUM(o.quantity) DESC
        """
    )
    fun getProductOrderStatsByQuantity(): List<ProductOrder>
}