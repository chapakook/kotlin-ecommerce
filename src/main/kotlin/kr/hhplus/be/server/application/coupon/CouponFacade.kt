package kr.hhplus.be.server.application.coupon

import kr.hhplus.be.server.domain.coupon.CouponCommand
import kr.hhplus.be.server.domain.coupon.CouponService
import kr.hhplus.be.server.domain.couponevent.CouponEventService
import kr.hhplus.be.server.support.annotation.DistributedLock
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.concurrent.TimeUnit

@Service
class CouponFacade(
    private val couponService: CouponService,
    private val couponEventService: CouponEventService,
) {
    @DistributedLock(key = "'lock:coupon:#cri.couponEventId'", timeout = 120000, timeUnit = TimeUnit.MILLISECONDS)
    @Transactional
    fun issue(cri: CouponCriteria.Issue): CouponResult.Issue =
        CouponResult.Issue.of(couponService.issue(cri.toIssue(couponEventService.issue(cri.toIssue()))))

    fun enqueue(cri: CouponCriteria.Enqueue): Boolean = couponEventService.enqueue(cri.to())

    fun send(cri: CouponCriteria.Send) {
        couponEventService.send(cri.to())
    }

    @Transactional
    fun processIssue(cri: CouponCriteria.Process) {
        val users = couponEventService.dequeue(cri.to())
        for (userId in users) {
            if (couponEventService.hasIssued(cri.toHasIssued(userId))) continue
            val couponEventInfo = couponEventService.issue(cri.toIssue())
            couponService.issue(
                CouponCommand.Issue(
                    userId,
                    couponEventInfo.type,
                    couponEventInfo.value,
                    couponEventInfo.expiryMillis
                )
            )
            couponEventService.set(cri.toSetCouponEvent(userId))
            couponEventService.remove(cri.toRemove(userId))
        }
    }
}