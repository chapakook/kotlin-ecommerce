package kr.hhplus.be.server.application.product

import kr.hhplus.be.server.application.product.ProductCriteria.Find
import kr.hhplus.be.server.application.product.ProductResult.Product
import kr.hhplus.be.server.application.product.ProductResult.Rank
import kr.hhplus.be.server.domain.product.ProductService
import kr.hhplus.be.server.domain.stock.StockService
import org.springframework.stereotype.Service

@Service
class ProductFacade(
    private val productService: ProductService,
    private val stockService: StockService,
) {
    fun find(findCri: Find): Product {
        val product = productService.find(findCri.toProductCmd())
        val stock = stockService.find(findCri.toProductStockCmd())
        return Product.of(product, stock)
    }

    fun rank(): List<Rank> = Rank.ofList(productService.rank())
}