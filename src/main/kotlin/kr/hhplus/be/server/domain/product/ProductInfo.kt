package kr.hhplus.be.server.domain.product

class  ProductInfo{
    data class Product(
        val productId: Long,
        val name: String,
        val price: Long,
        val stock: Long,
    ){
        init {
            require(productId > 0) { "productId must be positive" }
            require(name.isNotEmpty()) { "name must not be empty" }
            require(price > 0) { "price must be positive" }
            require(stock >= 0) { "stock must be not negative" }
        }
    }

    data class Rank(
        val productId: Long,
        val rank: Int,
        val quantity: Int,
    ){
        init {
            require(productId > 0) { "productId must be positive" }
            require(rank > 0) { "rank must be positive" }
        }
    }
}