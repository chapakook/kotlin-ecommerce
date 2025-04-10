package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Repository

@Repository
interface CouponRepository {
    fun findCouponByUserIdAndCode(userId: Long, code: String): CouponInfo.Coupon?
    fun insertCoupon():CouponInfo.Coupon
    fun updateCoupon():CouponInfo.Coupon
    fun insertUserCoupon():CouponInfo.Coupon
    fun updateUserCoupon():CouponInfo.Coupon
    fun insertOrderCoupon():CouponInfo.Coupon
    fun updateOrderCoupon():CouponInfo.Coupon
}