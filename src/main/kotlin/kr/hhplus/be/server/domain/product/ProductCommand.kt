package kr.hhplus.be.server.domain.product

class ProductCommand {
    data class Find(val productId: Long)
    data class Reduce(val productId: Long, val quantity: Int)
}