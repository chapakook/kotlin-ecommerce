package kr.hhplus.be.server.domain.product

class ProductInfo {
    class ProductInfo(
        val productId: Long,
        val name: String,
        val price: Long,
    ) {
        companion object {
            fun of(product: Product): ProductInfo = with(product) { ProductInfo(productId, name, price) }
        }
    }

    class ProductOrderInfo(
        val rank: Int,
        val productId: Long,
        val productName: String,
        val totalOrder: Long,
    ) {
        companion object {
            fun ofList(list: List<ProductRank>): List<ProductOrderInfo> =
                list.sortedByDescending { it.score }.mapIndexed { idx, item ->
                    with(item) {
                        ProductOrderInfo(
                            idx + 1,
                            productId,
                            name,
                            score.toLong(),
                        )
                    }
                }
        }
    }
}