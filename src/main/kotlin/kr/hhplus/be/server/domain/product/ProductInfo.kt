package kr.hhplus.be.server.domain.product

class  ProductInfo{
    data class Product(
        val productId: Long,
        val name: String,
        val price: Long,
        val stock: Long,
    )
    data class Rank(
        val productId: Long,
        val rank: Int,
        val quantity: Int,
    )
}