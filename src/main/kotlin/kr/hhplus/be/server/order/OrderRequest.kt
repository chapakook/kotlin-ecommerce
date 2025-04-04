package kr.hhplus.be.server.order

data class OrderRequest(
    val productId: Long,
    val quantity: Int,
)
