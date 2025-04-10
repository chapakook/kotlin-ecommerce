package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Repository

@Repository
interface CouponRepository {
    fun findCouponByUserIdAndCode(userId: Long, code: String): CouponInfo.Coupon?
    fun insert():CouponInfo.Coupon
    fun update():CouponInfo.Coupon
}