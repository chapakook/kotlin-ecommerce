package kr.hhplus.be.server.application.coupon

import kr.hhplus.be.server.domain.coupon.CouponInfo

class CouponResult {
    class Issue(
        val couponId: Long,
        val expiryMillis: Long,
    ) {
        companion object {
            fun of(info: CouponInfo.Issue): Issue = with(info) { Issue(couponId, expiryMillis) }
        }
    }
}