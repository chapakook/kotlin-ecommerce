package kr.hhplus.be.server.application.product

import kr.hhplus.be.server.domain.product.ProductCommand
import kr.hhplus.be.server.domain.stock.StockCommand

class ProductCriteria {
    class Find(
        val productId: Long,
    ) {
        fun toProductCmd(): ProductCommand.Find = ProductCommand.Find(productId)
        fun toProductStockCmd(): StockCommand.Find = StockCommand.Find(productId)
    }
}