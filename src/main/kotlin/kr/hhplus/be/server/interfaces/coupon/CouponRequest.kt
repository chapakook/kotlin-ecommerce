package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.application.coupon.CouponCriteria

class CouponRequest {
    data class Issue(
        val userId: Long,
        val couponId: Long,
    ) {
        fun toCriteria(): CouponCriteria.Issue = CouponCriteria.Issue(userId, couponId)
    }
}