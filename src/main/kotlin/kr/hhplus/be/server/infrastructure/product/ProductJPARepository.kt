package kr.hhplus.be.server.infrastructure.product

import kr.hhplus.be.server.domain.product.Product
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductJPARepository : JpaRepository<Product, Long> {
    fun findProductById(productId: Long): Product?
}