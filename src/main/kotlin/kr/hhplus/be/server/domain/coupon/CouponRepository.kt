package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Repository

@Repository
interface CouponRepository {
    fun findByCouponIdAndUserId(couponId: Long, userId: Long): Coupon?
    fun save(coupon: Coupon): Coupon
}
