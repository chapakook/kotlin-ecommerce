package kr.hhplus.be.server.infrastructure.couponevent

import jakarta.persistence.LockModeType
import kr.hhplus.be.server.domain.couponevent.CouponEvent
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface CouponEventJPARepository : JpaRepository<CouponEvent, Long> {
    fun findByCouponEventId(couponEventId: Long): CouponEvent?
    fun save(event: CouponEvent): CouponEvent

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT ce FROM CouponEvent ce WHERE ce.couponEventId = :couponEventId")
    fun findByCouponEventIdWithPessimisticLock(couponEventId: Long): CouponEvent?
}