package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.domain.coupon.CouponInfo.UserCoupon
import java.time.LocalDateTime

class CouponResponse {
    data class IssueResult(
        val couponId: Long,
        val expiredAt: LocalDateTime,
    ) {
        companion object {
            fun of(coupon: UserCoupon): IssueResult = with(coupon) { IssueResult(couponId, expiredAt) }
        }
    }
}