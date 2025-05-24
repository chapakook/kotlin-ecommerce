package kr.hhplus.be.server.infrastructure.couponevent

import kr.hhplus.be.server.domain.couponevent.CouponEvent
import kr.hhplus.be.server.domain.couponevent.CouponEventRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CouponEventRepositoryImpl(
    private val jpaCouponEventRepository: JpaCouponEventRepository,
) : CouponEventRepository {
    override fun findByCouponEventId(couponEventId: Long): CouponEvent? =
        jpaCouponEventRepository.findByCouponEventId(couponEventId)

    @Transactional
    override fun save(event: CouponEvent): CouponEvent = jpaCouponEventRepository.save(event)
    override fun findByCouponEventIdWithPessimisticLock(couponEventId: Long): CouponEvent? =
        jpaCouponEventRepository.findByCouponEventIdWithPessimisticLock(couponEventId)
}