package kr.hhplus.be.server.domain.product

class ProductCommand {
    data class Get (
        val productId: Long
    ){
        init {
            require(productId > 0) { "Product ID must be positive." }
        }
    }
}