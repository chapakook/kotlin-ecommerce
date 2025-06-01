package kr.hhplus.be.server.domain.product

class ProductCommand {
    data class Find(val productId: Long)
    data class Rank(val productId: Long, val quantity: Int)
}