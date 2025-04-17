package kr.hhplus.be.server.application.product

import kr.hhplus.be.server.application.product.ProductCriteria.Find
import kr.hhplus.be.server.application.product.ProductResult.Product
import kr.hhplus.be.server.application.product.ProductResult.Rank
import kr.hhplus.be.server.domain.popularity.PopularityService
import kr.hhplus.be.server.domain.product.ProductService
import kr.hhplus.be.server.domain.stock.StockService
import org.springframework.stereotype.Service

@Service
class ProductFacade(
    val productService: ProductService,
    val stockService: StockService,
    val popularityService: PopularityService,
) {
    fun find(findCri: Find): Product {
        val product = productService.find(findCri.toProductCmd())
        val stock = stockService.find(findCri.toProductStockCmd())
        return Product.of(product, stock)
    }
    
    fun ranks(): List<Rank> = Rank.ofList(popularityService.ranks())
}