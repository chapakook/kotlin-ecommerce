package kr.hhplus.be.server.domain.coupon

import java.time.LocalDateTime

class CouponInfo {
    data class Coupon(
        val couponId: Long,
        val issuedLimit: Int,
        val issuedCount: Int,
    )
    data class UserCoupon(
        val userId: Long,
        val couponId: Long,
        val code: String,
        val expiredAt: LocalDateTime,
        val isUsed: Boolean,
    )
    data class OrderCoupon(
        val orderCouponId: Long,
        val couponId: Long,
        val orderId: Long,
        val appliedDiscount: Long,
        val appliedAt: LocalDateTime,
    )
}