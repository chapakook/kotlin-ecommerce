package kr.hhplus.be.server.infrastructure.couponevent

import kr.hhplus.be.server.domain.couponevent.CouponEvent
import kr.hhplus.be.server.domain.couponevent.CouponEventRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class CouponEventRepositoryImpl(
    private val couponEventJPARepository: CouponEventJPARepository,
) : CouponEventRepository {
    override fun findByCouponEventId(couponEventId: Long): CouponEvent? =
        couponEventJPARepository.findByCouponEventId(couponEventId)?.to()

    @Transactional
    override fun save(event: CouponEvent): CouponEvent = couponEventJPARepository.save(CouponEventEntity.of(event)).to()
}