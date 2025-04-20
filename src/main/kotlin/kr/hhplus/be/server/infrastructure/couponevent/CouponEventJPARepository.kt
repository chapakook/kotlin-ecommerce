package kr.hhplus.be.server.infrastructure.couponevent

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CouponEventJPARepository : JpaRepository<CouponEventEntity, Long> {
    fun findCouponEventById(id: Long): CouponEventEntity?
    fun save(event: CouponEventEntity): CouponEventEntity
}