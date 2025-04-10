package kr.hhplus.be.server.domain.product

class  ProductInfo{
    data class Product(
        val productId: Long,
        val name: String,
        val price: Long,
    ){
        init {
            require(productId > 0) { "productId must be positive." }
            require(name.isNotEmpty()) { "name must not be empty." }
            require(price > 0) { "price must be positive." }
        }
    }

    data class Rank(
        val productId: Long,
        val rank: Int,
        val quantity: Int,
    ){
        init {
            require(productId > 0) { "productId must be positive." }
            require(rank > 0) { "rank must be positive." }
        }
    }
}