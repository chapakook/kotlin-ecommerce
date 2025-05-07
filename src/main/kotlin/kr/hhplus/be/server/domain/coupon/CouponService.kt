package kr.hhplus.be.server.domain.coupon

import kr.hhplus.be.server.support.ErrorCode.COUPON_NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponService(
    private val couponRepository: CouponRepository,
) {
    fun find(cmd: CouponCommand.Find): CouponInfo.Find =
        couponRepository.findByCouponIdAndUserId(cmd.couponId, cmd.userId)
            ?.let { coupon -> CouponInfo.Find.of(coupon) }
            ?: throw NoSuchElementException(COUPON_NOT_FOUND.message)

    @Transactional
    fun issue(cmd: CouponCommand.Issue): CouponInfo.Issue =
        CouponInfo.Issue.of(couponRepository.save(with(cmd) { Coupon.issue(userId, type, value, expiryMillis) }))

    @Transactional
    fun use(cmd: CouponCommand.Use): Long = cmd.couponId?.let { couponId ->
        couponRepository.findByCouponIdAndUserId(couponId, cmd.userId)?.let { coupon ->
            val amount = coupon.use(cmd.amount)
            couponRepository.save(coupon)
            amount
        } ?: cmd.amount
    } ?: cmd.amount
}