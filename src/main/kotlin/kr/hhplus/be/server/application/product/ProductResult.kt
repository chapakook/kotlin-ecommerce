package kr.hhplus.be.server.application.product

import kr.hhplus.be.server.domain.popularity.PopularityInfo.PopularityInfo
import kr.hhplus.be.server.domain.product.ProductInfo.ProductInfo
import kr.hhplus.be.server.domain.stock.StockInfo.StockInfo

class ProductResult {
    class Product(
        val productId: Long,
        val name: String,
        val price: Long,
        val stock: Int,
    ) {
        companion object {
            fun of(info: ProductInfo, stock: StockInfo): Product =
                with(info) { Product(productId, name, price, stock.quantity) }
        }
    }

    class Rank(
        val popularityId: Long,
        val productId: Long,
        val productName: String,
        val rank: Int,
        val totalOrder: Int,
        val updateMills: Long,
    ) {
        companion object {
            fun ofList(popularity: List<PopularityInfo>): List<Rank> =
                popularity.map { p ->
                    Rank(
                        p.popularityId,
                        p.productId,
                        p.productName,
                        p.rank,
                        p.totalOrder,
                        p.updateMills
                    )
                }
        }
    }
}