package kr.hhplus.be.server.infrastructure.couponevent

import kr.hhplus.be.server.domain.couponevent.CouponEvent
import kr.hhplus.be.server.domain.couponevent.CouponEventRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CouponEventRepositoryImpl(
    private val couponEventJPARepository: CouponEventJPARepository,
) : CouponEventRepository {
    override fun findCouponEventByCouponEventId(couponEventId: Long): CouponEvent? =
        couponEventJPARepository.findCouponEventByCouponEventId(couponEventId)

    @Transactional
    override fun save(couponEvent: CouponEvent): CouponEvent = couponEventJPARepository.save(couponEvent)
}