package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CouponService (
    val couponRepository: CouponRepository,
) {
    fun use(cmd:CouponCommand.Use): CouponInfo.UserCoupon?{
        if (cmd.code.isNullOrBlank()) return null
        val coupon = couponRepository.findCouponByUserIdAndCode(cmd.userId, cmd.code) ?: return null
        if (coupon.isUsed) return null
        if (coupon.expiredAt.toLocalDate() < LocalDateTime.now().toLocalDate()) return null
        couponRepository.insertOrderCoupon()
        return couponRepository.insertUserCoupon()
    }
    fun issue(cmd:CouponCommand.Issue):CouponInfo.UserCoupon?{
        val coupon = couponRepository.findCouponByCouponId(cmd.couponId)
        if (coupon.issuedLimit <= coupon.issuedCount) return null
        val find = couponRepository.findCouponByCouponIdAndUserId(cmd.couponId, cmd.userId )
        if (find != null ) return null
        couponRepository.updateCoupon()
        return couponRepository.insertUserCoupon()
    }
}