package kr.hhplus.be.server.infrastructure.product

import kr.hhplus.be.server.domain.product.Product
import kr.hhplus.be.server.domain.product.ProductOrder
import kr.hhplus.be.server.domain.product.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val jpaProductRepository: JpaProductRepository,
) : ProductRepository {
    override fun findTop10(): List<Product> = jpaProductRepository.findTop10()
    override fun findByProductId(productId: Long): Product? = jpaProductRepository.findByProductId(productId)
    override fun getProductOrderStatsByQuantity(): List<ProductOrder> =
        jpaProductRepository.getProductOrderStatsByQuantity()
}