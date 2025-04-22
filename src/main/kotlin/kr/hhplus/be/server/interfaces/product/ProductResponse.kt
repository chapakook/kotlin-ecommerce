package kr.hhplus.be.server.interfaces.product

import kr.hhplus.be.server.application.product.ProductResult.Product
import kr.hhplus.be.server.application.product.ProductResult.Rank


class ProductResponse {
    class ProductV1(
        val product: Product,
    ) {
        companion object {
            fun of(product: Product): ProductV1 = ProductV1(product)
        }
    }

    class RankV1(
        val productId: Long,
        val productName: String,
        val rank: Int,
    ) {
        companion object {
            fun ofList(ranks: List<Rank>): List<RankV1> = ranks.map { r -> RankV1(r.productId, r.productName, r.rank) }
        }
    }
}