package kr.hhplus.be.server.interfaces.product

import kr.hhplus.be.server.application.product.ProductResult.Product
import kr.hhplus.be.server.application.product.ProductResult.Rank


class ProductResponse {
    class ProductV1(
        val productId: Long,
        val name: String,
        val price: Long,
        val stock: Int,
    ) {
        companion object {
            fun of(product: Product): ProductV1 = with(product) { ProductV1(productId, name, price, stock) }
        }
    }

    class RankV1(
        val rank: Int,
        val productId: Long,
        val productName: String,
        val totalOrder: Long,
    ) {
        companion object {
            fun ofList(list: List<Rank>): List<RankV1> = list.map { item ->
                with(item) { RankV1(rank, productId, productName, totalOrder) }
            }
        }
    }
}