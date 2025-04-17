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
}