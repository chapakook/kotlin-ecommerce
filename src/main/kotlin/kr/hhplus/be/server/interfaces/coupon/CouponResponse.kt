package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.application.coupon.CouponResult

class CouponResponse {
    data class IssueV1(
        val couponId: Long,
        val expiryMillis: Long,
    ) {
        companion object {
            fun of(result: CouponResult.Issue): IssueV1 = with(result) { IssueV1(couponId, expiryMillis) }
        }
    }
}