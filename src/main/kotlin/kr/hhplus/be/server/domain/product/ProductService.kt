package kr.hhplus.be.server.domain.product

import org.springframework.stereotype.Service

@Service
class ProductService (
    private val productRepository: ProductRepository,
){
    fun getProduct(getCmd: ProductCommand.Get): ProductInfo.Product{
        return productRepository.findProductById(getCmd.productId)
    }

    fun reduceProduct(reduceCmd: ProductCommand.Reduce): ProductInfo.Product{
        val product = productRepository.findProductById(reduceCmd.productId)
        require( product.stock > 0) { "상품재고가 없습니다" }
        require( product.stock >=  reduceCmd.quantity) { "재고보다 많은 수량을 주문했습니다" }
        return productRepository.update()
    }

    fun getRank():List<ProductInfo.Rank>{
        return emptyList()
    }
}