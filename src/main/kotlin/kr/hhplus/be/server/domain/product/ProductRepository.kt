package kr.hhplus.be.server.domain.product

import org.springframework.stereotype.Repository

@Repository
interface ProductRepository {
    fun findRank(): List<ProductInfo.Rank>
    fun findProductById(id: Long): ProductInfo.Product
    fun update(): ProductInfo.Product
}