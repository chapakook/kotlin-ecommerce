package kr.hhplus.be.server.domain.product

import kr.hhplus.be.server.domain.product.ProductInfo.ProductInfo
import kr.hhplus.be.server.support.ErrorCode.POPULARITY_NOT_FOUNT
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    @Transactional
    fun find(getCmd: ProductCommand.Find): ProductInfo =
        productRepository.findByProductId(getCmd.productId)?.let { product: Product ->
            ProductInfo.of(product)
        } ?: throw NoSuchElementException(POPULARITY_NOT_FOUNT.message)
}