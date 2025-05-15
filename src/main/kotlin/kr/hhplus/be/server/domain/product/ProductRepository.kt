package kr.hhplus.be.server.domain.product

import org.springframework.stereotype.Repository

@Repository
interface ProductRepository {
    fun findByProductId(productId: Long): Product?
    fun getProductOrderStatsByQuantity(): List<ProductOrder>
}