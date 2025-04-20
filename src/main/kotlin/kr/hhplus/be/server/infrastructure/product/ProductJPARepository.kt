package kr.hhplus.be.server.infrastructure.product

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductJPARepository : JpaRepository<ProductEntity, Long> {
    fun findProductById(id: Long): ProductEntity?
}