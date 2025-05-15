package kr.hhplus.be.server.infrastructure.product

import kr.hhplus.be.server.domain.product.Product
import kr.hhplus.be.server.domain.product.ProductOrder
import kr.hhplus.be.server.domain.product.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val productJPARepository: ProductJPARepository,
) : ProductRepository {
    override fun findByProductId(productId: Long): Product? = productJPARepository.findByProductId(productId)
    override fun getProductOrderStatsByQuantity(): List<ProductOrder> =
        productJPARepository.getProductOrderStatsByQuantity()
}