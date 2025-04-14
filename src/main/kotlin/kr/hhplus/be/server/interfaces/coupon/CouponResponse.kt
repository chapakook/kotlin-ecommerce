package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.domain.coupon.CouponInfo
import java.time.LocalDateTime

class CouponResponse {
    data class IssuedCoupon(
        val couponId: Long,
        val expiredAt: LocalDateTime,
    ) {
        companion object {
            fun of(coupon: CouponInfo.UserCoupon): IssuedCoupon = with(coupon) { IssuedCoupon(couponId, expiredAt) }
        }
    }
}