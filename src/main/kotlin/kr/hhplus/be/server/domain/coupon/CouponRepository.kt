package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Repository

@Repository
interface CouponRepository {
    fun findCouponByUserIdAndId(userId: Long, couponId: Long): Coupon?
    fun save(coupon: Coupon): Coupon
}
