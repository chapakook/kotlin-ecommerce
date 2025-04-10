package kr.hhplus.be.server.domain.order

class OrderCommand {
    data class Order(
        val productId: Long,
        val price: Long,
        val quantity: Int,
    ){
        init {
            require(productId > 0) { "Product id must be positive" }
            require(price > 0) { "Price must be positive" }
            require(quantity > 0) { "Quantity must be positive" }
        }
    }
}