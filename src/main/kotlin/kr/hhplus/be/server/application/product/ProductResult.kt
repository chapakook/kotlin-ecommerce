package kr.hhplus.be.server.application.product

import kr.hhplus.be.server.domain.product.ProductInfo
import kr.hhplus.be.server.domain.stock.StockInfo.StockInfo

class ProductResult {
    class Product(
        val productId: Long,
        val name: String,
        val price: Long,
        val stock: Int,
    ) {
        companion object {
            fun of(info: ProductInfo.ProductInfo, stock: StockInfo): Product =
                with(info) { Product(productId, name, price, stock.quantity) }
        }
    }
    
    class Rank(
        val rank: Int,
        val productId: Long,
        val productName: String,
        val totalOrder: Long,
    ) {
        companion object {
            fun ofList(list: List<ProductInfo.ProductOrderInfo>): List<Rank> =
                list.map { item ->
                    with(item) { Rank(rank, productId, productName, totalOrder) }
                }
        }
    }
}