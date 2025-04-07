package kr.hhplus.be.server.product

import java.time.LocalDateTime

data class Product(
    val  productId: Long,
    val name: String,
    val price: Int,
    val createdAt: LocalDateTime,
)
