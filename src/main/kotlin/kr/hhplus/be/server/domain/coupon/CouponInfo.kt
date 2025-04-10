package kr.hhplus.be.server.domain.coupon

import java.time.LocalDateTime
import java.util.UUID

class CouponInfo {
    data class Coupon(
        val couponId: Long,
        val code: String,
        val expiredAt: LocalDateTime,
        val isUsed: Boolean,
    )
}