package kr.hhplus.be.server.interfaces.product

import kr.hhplus.be.server.domain.product.ProductInfo.Product
import kr.hhplus.be.server.domain.product.ProductInfo.Rank

class ProductResponse {
    class ProductResult(
        val productId: Long,
        val name: String,
        val price: Long,
        val quantity: Int,
    ) {
        companion object {
            fun from(product: Product): ProductResult = with(product) { ProductResult(productId, name, price, stock) }
        }
    }

    class RankResult(
        val productId: Long,
        val rank: Int,
    ) {
        companion object {
            fun of(ranks: List<Rank>): List<RankResult> = ranks.map { r -> with(r) { RankResult(productId, rank) } }
        }
    }
}