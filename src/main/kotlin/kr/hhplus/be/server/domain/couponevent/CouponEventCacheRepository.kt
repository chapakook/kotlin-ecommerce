package kr.hhplus.be.server.domain.couponevent

interface CouponEventCacheRepository {
    fun set(couponEventId: Long, userId: Long)
    fun existsByUserIdAndCouponEventId(userId: Long, couponEventId: Long): Boolean
}