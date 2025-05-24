package kr.hhplus.be.server.domain.coupon

class CouponCommand {
    class Use(
        val userId: Long,
        val couponId: Long?,
        val amount: Long,
    )

    class Issue(
        val userId: Long,
        val type: CouponType,
        val value: Long,
        val expiryMillis: Long,
    )

    class Find(
        val userId: Long,
        val couponId: Long,
    )

    class Restore(
        val userId: Long,
        val couponId: Long,
    )
}