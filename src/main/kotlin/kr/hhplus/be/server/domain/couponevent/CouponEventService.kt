package kr.hhplus.be.server.domain.couponevent

import kr.hhplus.be.server.domain.couponevent.CouponEventCommand.*
import kr.hhplus.be.server.support.ErrorCode.COUPON_EVENT_NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponEventService(
    private val couponEventRepository: CouponEventRepository,
    private val couponQueueRepository: CouponQueueRepository,
    private val couponEventCacheRepository: CouponEventCacheRepository,
    private val outsideCouponEventEventProducer: OutsideCouponEventEventProducer
) {
    fun find(cmd: Find): CouponEventInfo.Find =
        couponEventRepository.findByCouponEventId(cmd.couponEventId)?.let { couponEvent ->
            CouponEventInfo.Find.of(couponEvent)
        } ?: throw NoSuchElementException(COUPON_EVENT_NOT_FOUND.message)

    @Transactional
    fun issue(cmd: Issue): CouponEventInfo.Issue =
        couponEventRepository.findByCouponEventId(cmd.couponEventId)?.let { couponEvent ->
            couponEvent.issue()
            couponEventRepository.save(couponEvent)
            CouponEventInfo.Issue.of(couponEvent)
        } ?: throw NoSuchElementException(COUPON_EVENT_NOT_FOUND.message)

    fun enqueue(cmd: Enqueue): Boolean = couponQueueRepository
        .enqueue(cmd.couponEventId, cmd.userId)

    fun dequeue(cmd: Dequeue): List<Long> =
        couponQueueRepository.dequeue(cmd.couponEventId, cmd.count)

    fun remove(cmd: Remove) {
        couponQueueRepository.remove(cmd.couponEventId, cmd.userId)
    }

    fun hasIssued(cmd: HasIssued): Boolean =
        couponEventCacheRepository.existsByUserIdAndCouponEventId(cmd.userId, cmd.couponEventId)

    fun set(cmd: SetCouponEvent) {
        couponEventCacheRepository.set(cmd.couponEventId, cmd.userId)
    }

    fun send(cmd: Send) {
        outsideCouponEventEventProducer.send(CouponEventEvent.CouponIssueRequest(cmd.couponEventId, cmd.userId))
    }
}