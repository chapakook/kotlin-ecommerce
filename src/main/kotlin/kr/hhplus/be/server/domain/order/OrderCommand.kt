package kr.hhplus.be.server.domain.order

class OrderCommand {
    data class Order(
        val productId: Long,
        val quantity: Int
    ){
        init {
            require(productId > 0) { "Product id must be positive" }
        }
    }
}