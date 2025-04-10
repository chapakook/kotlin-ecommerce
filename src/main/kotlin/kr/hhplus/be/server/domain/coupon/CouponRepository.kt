package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Repository

@Repository
interface CouponRepository {
    fun findCouponByCouponId(couponId: Long): CouponInfo.Coupon
    fun findCouponByCouponIdAndUserId(couponId: Long, userId: Long): CouponInfo.UserCoupon?
    fun findCouponByUserIdAndCode(userId: Long, code: String): CouponInfo.UserCoupon?
    fun insertCoupon():CouponInfo.Coupon
    fun updateCoupon():CouponInfo.Coupon
    fun insertUserCoupon():CouponInfo.UserCoupon
    fun updateUserCoupon():CouponInfo.UserCoupon
    fun insertOrderCoupon():CouponInfo.OrderCoupon
    fun updateOrderCoupon():CouponInfo.OrderCoupon
}