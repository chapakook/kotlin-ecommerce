package kr.hhplus.be.server.domain.couponevent

import kr.hhplus.be.server.domain.coupon.CouponType

class CouponEventInfo {
    class Find(
        val couponEventId: Long,
        val maxCount: Int,
        var currentCount: Int,
        val type: CouponType,
        val value: Long,
        val expiryMillis: Long,
    ) {
        companion object {
            fun of(couponEvent: CouponEvent): Find =
                with(couponEvent) { Find(couponEventId, maxCount, currentCount, type, value, expiryMillis) }
        }
    }

    class Issue(
        val couponEventId: Long,
        val maxCount: Int,
        var currentCount: Int,
        val type: CouponType,
        val value: Long,
        val expiryMillis: Long,
    ) {
        companion object {
            fun of(couponEvent: CouponEvent): Issue =
                with(couponEvent) { Issue(couponEventId, maxCount, currentCount, type, value, expiryMillis) }
        }
    }
}