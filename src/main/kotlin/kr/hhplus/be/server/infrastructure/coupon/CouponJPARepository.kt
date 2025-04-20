package kr.hhplus.be.server.infrastructure.coupon

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponJPARepository : JpaRepository<CouponEntity, Long> {
    fun findCouponByUserIdAndId(userId: Long, id: Long): CouponEntity?
    fun save(coupon: CouponEntity): CouponEntity
}