package kr.hhplus.be.server.infrastructure.couponevent

import kr.hhplus.be.server.domain.couponevent.CouponEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponEventJPARepository : JpaRepository<CouponEvent, Long> {
    fun findCouponEventByCouponEventId(couponEventId: Long): CouponEvent?
    fun save(couponEvent: CouponEvent): CouponEvent
}