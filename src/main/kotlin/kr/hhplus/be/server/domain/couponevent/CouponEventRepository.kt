package kr.hhplus.be.server.domain.couponevent

import org.springframework.stereotype.Repository

@Repository
interface CouponEventRepository {
    fun findCouponEventByCouponEventId(couponEventId: Long): CouponEvent?
    fun save(couponEvent: CouponEvent): CouponEvent
}