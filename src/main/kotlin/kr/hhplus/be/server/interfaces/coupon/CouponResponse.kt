package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.domain.coupon.CouponInfo
import java.time.LocalDateTime

class CouponResponse {
    data class Coupon(val couponId: Long, val expiredAt: LocalDateTime)
    fun ofCoupon(coupon: CouponInfo.Coupon): Coupon = with(coupon){ Coupon(couponId, expiredAt) }
}