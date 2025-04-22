package kr.hhplus.be.server.infrastructure.product

import kr.hhplus.be.server.domain.product.Product
import kr.hhplus.be.server.domain.product.ProductRepository
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val productJPARepository: ProductJPARepository,
) : ProductRepository {
    override fun findProductByProductId(productId: Long): Product? =
        productJPARepository.findProductByProductId(productId)
}