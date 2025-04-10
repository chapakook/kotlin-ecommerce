package kr.hhplus.be.server.domain.product

import org.springframework.stereotype.Service

@Service
class ProductService {
    fun getProduct(getCmd: ProductCommand.Get): ProductInfo.Product{
        return ProductInfo.Product(1L,"Product",100L)
    }

    fun getRank(rankCmd: ProductCommand.Rank):List<ProductInfo.Rank>{
        return emptyList()
    }
}