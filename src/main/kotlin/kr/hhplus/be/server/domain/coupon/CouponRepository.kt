package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Repository

@Repository
interface CouponRepository {
    fun findCouponByIdAndUserId(id: Long, userId: Long): Coupon?
    fun save(coupon: Coupon): Coupon
}
