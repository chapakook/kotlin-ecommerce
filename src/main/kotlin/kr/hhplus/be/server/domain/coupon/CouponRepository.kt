package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Repository

@Repository
interface CouponRepository {
    fun findCouponByUserIdAndCouponId(userId: Long, couponId: Long): Coupon?
    fun save(coupon: Coupon): Coupon
}
