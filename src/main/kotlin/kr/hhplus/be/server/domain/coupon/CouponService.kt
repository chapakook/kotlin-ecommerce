package kr.hhplus.be.server.domain.coupon

import kr.hhplus.be.server.support.ErrorCode.COUPON_NOT_FOUND
import org.springframework.stereotype.Service

@Service
class CouponService(
    val couponRepository: CouponRepository,
) {
    fun find(cmd: CouponCommand.Find): CouponInfo.Find =
        couponRepository.findCouponByUserIdAndId(cmd.userId, cmd.couponId)?.let { coupon -> CouponInfo.Find.of(coupon) }
            ?: throw NoSuchElementException(COUPON_NOT_FOUND.message)

    fun issue(cmd: CouponCommand.Issue): CouponInfo.Issue =
        CouponInfo.Issue.of(couponRepository.save(with(cmd) { Coupon.issue(userId, type, value, expiryMillis) }))

    fun use(cmd: CouponCommand.Use): Long = cmd.couponId?.let { couponId ->
        couponRepository.findCouponByUserIdAndId(cmd.userId, couponId)?.let { coupon ->
            coupon.use(cmd.amount)
        } ?: cmd.amount
    } ?: cmd.amount
}