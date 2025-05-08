package kr.hhplus.be.server.domain.order

data class OrderProduct(
    val productId: Long,
    val productName: String,
    val totalOrder: Long
)