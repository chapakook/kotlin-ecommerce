package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.domain.coupon.CouponCommand

class CouponRequest {
    data class Issue(
        val userId: Long,
        val couponId: Long,
    ) {
        fun to(): CouponCommand.Issue = CouponCommand.Issue(userId, couponId)
    }
}