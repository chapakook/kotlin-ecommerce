package kr.hhplus.be.server.domain.product

import org.springframework.stereotype.Repository

@Repository
interface ProductRepository {
    fun findProductByProductId(productId: Long): Product?
}