package kr.hhplus.be.server.infrastructure.coupon

import kr.hhplus.be.server.domain.coupon.Coupon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponJPARepository : JpaRepository<Coupon, Long> {
    fun findCouponByUserIdAndCouponId(userId: Long, couponId: Long): Coupon?
    fun save(coupon: Coupon): Coupon
}