package kr.hhplus.be.server.infrastructure.coupon

import kr.hhplus.be.server.domain.coupon.Coupon
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaCouponRepository : JpaRepository<Coupon, Long> {
    fun findByCouponIdAndUserId(couponId: Long, userId: Long): Coupon?
    fun save(coupon: Coupon): Coupon
}