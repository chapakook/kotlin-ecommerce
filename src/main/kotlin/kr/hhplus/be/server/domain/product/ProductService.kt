package kr.hhplus.be.server.domain.product

import kr.hhplus.be.server.support.ErrorCode.POPULARITY_NOT_FOUNT
import org.springframework.stereotype.Service

@Service
class ProductService(
    private val productRepository: ProductRepository,
    private val productCacheRepository: ProductCacheRepository,
    private val productRankingRepository: ProductRankingRepository
) {
    fun find(cmd: ProductCommand.Find): ProductInfo.ProductInfo = (
            productCacheRepository.findByProductId(cmd.productId) ?: {
                productRepository.findByProductId(cmd.productId)?.let { product: Product ->
                    val result = ProductInfo.ProductInfo.of(product)
                    with(result) { productCacheRepository.set(productId, name, price) }
                    result
                } ?: throw NoSuchElementException(POPULARITY_NOT_FOUNT.message)
            }) as ProductInfo.ProductInfo

    fun rank(): List<ProductInfo.ProductOrderInfo> = ProductInfo.ProductOrderInfo
        .ofList(productRankingRepository.findAllByDays(days = 3))

    fun increaseRank(cmd: ProductCommand.Rank) {
        productRankingRepository.increaseProductRank(
            productId = cmd.productId,
            scoreDelta = cmd.quantity.toDouble()
        )
    }
}