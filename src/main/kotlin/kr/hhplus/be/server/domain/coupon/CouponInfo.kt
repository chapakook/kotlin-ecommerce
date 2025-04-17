package kr.hhplus.be.server.domain.coupon

class CouponInfo {
    class Find(
        val couponId: Long,
        val userId: Long,
        val type: CouponType,
        val value: Long,
        val expiryMillis: Long,
        val createMillis: Long,
        var updateMillis: Long,
        var isActive: Boolean,
    ) {
        companion object {
            fun of(coupon: Coupon): Find =
                with(coupon) { Find(couponId, userId, type, value, expiryMillis, createMillis, updateMillis, isActive) }
        }
    }

    class Issue(
        val couponId: Long,
        val userId: Long,
        val type: CouponType,
        val value: Long,
        val expiryMillis: Long,
        val createMillis: Long,
        var updateMillis: Long,
        var isActive: Boolean,
    ) {
        companion object {
            fun of(coupon: Coupon): Issue =
                with(coupon) {
                    Issue(
                        couponId,
                        userId,
                        type,
                        value,
                        expiryMillis,
                        createMillis,
                        updateMillis,
                        isActive
                    )
                }
        }
    }
}