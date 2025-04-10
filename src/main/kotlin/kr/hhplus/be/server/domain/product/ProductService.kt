package kr.hhplus.be.server.domain.product

import org.springframework.stereotype.Service

@Service
class ProductService (
    private val productRepository: ProductRepository,
){
    fun getProduct(getCmd: ProductCommand.Get): ProductInfo.Product{
        return productRepository.findProductById(getCmd.productId)
    }

    fun getRank():List<ProductInfo.Rank>{
        return emptyList()
    }
}