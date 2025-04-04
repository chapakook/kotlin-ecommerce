package kr.hhplus.be.server.discount

import java.time.LocalDateTime

enum class DiscountType {
    PERCENTAGE,
    FIXED
}

data class Discount(
    val discountId: Long,
    val productId: Long,
    val discountType: DiscountType,
    val value: Int,
    val expirationDate: LocalDateTime
)
