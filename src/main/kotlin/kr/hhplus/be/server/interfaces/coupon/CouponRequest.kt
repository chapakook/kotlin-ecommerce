package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.application.coupon.CouponCriteria

class CouponRequest {
    data class Issue(
        val userId: Long,
        val couponId: Long,
    ) {
        fun to(): CouponCriteria.Send = CouponCriteria.Send(couponId, userId)
    }
}