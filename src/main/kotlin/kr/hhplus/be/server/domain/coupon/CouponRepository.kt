package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Repository

@Repository
interface CouponRepository {
    fun findCouponByUserIdAndId(userId: Long, id: Long): Coupon?
    fun save(coupon: Coupon): Coupon
}
