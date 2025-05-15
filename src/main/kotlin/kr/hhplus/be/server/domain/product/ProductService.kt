package kr.hhplus.be.server.domain.product

import kr.hhplus.be.server.support.ErrorCode.POPULARITY_NOT_FOUNT
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
) {
    fun find(getCmd: ProductCommand.Find): ProductInfo.ProductInfo =
        productRepository.findByProductId(getCmd.productId)?.let { product: Product ->
            ProductInfo.ProductInfo.of(product)
        } ?: throw NoSuchElementException(POPULARITY_NOT_FOUNT.message)

    @Cacheable(cacheNames = ["rank"], key = "'ALL'")
    fun rank(): List<ProductInfo.ProductOrderInfo> = ProductInfo.ProductOrderInfo
        .ofList(productRepository.getProductOrderStatsByQuantity())
}