package kr.hhplus.be.server.domain.couponevent

import org.springframework.stereotype.Repository

@Repository
interface CouponEventRepository {
    fun findCouponEventById(id: Long): CouponEvent?
    fun save(event: CouponEvent): CouponEvent
}