package kr.hhplus.be.server.domain.product

class ProductCommand {
    data class Get (
        val productId: Long
    ){
        init {
            require(productId > 0) { "Product ID must be positive." }
        }
    }
    data class Reduce(
        val productId: Long,
        val quantity: Int
    ){
        init {
            require(productId > 0) { "Product ID must be positive." }
            require(quantity > 0) { "Quantity must be positive." }
        }
    }
}