package kr.hhplus.be.server.domain.product

import org.springframework.stereotype.Service

@Service
class ProductService (
    private val productRepository: ProductRepository,
){
    fun find(getCmd: ProductCommand.Find): ProductInfo.Product{
        return productRepository.findProductById(getCmd.productId)
    }

    fun reduce(reduceCmd: ProductCommand.Reduce): ProductInfo.Product{
        val product = productRepository.findProductById(reduceCmd.productId)
        require( product.stock > 0) { "상품재고가 없습니다" }
        require( product.stock >=  reduceCmd.quantity) { "재고보다 많은 수량을 주문했습니다" }
        return productRepository.update()
    }

    fun ranks():List<ProductInfo.Rank>{
        return productRepository.findRank()
    }
}