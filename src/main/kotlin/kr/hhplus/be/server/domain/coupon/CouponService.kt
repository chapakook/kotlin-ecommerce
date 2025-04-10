package kr.hhplus.be.server.domain.coupon

import kr.hhplus.be.server.domain.order.OrderRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CouponService (
    val couponRepository: CouponRepository,
) {
    fun use(cmd:CouponCommand.Use): CouponInfo.Coupon?{
        if (cmd.code.isNullOrBlank()) return null
        val coupon = couponRepository.findCouponByUserIdAndCode(cmd.userId, cmd.code)
        require(coupon != null) { "해당 쿠폰이 없습니다." }
        require(!coupon.isUsed) { "이미 사용한 쿠폰입니다." }
        require(coupon.expiredAt >= LocalDateTime.now()) { "사용기간이 만료된 쿠폰입니다" }
        return couponRepository.update()
    }
    fun issue(cmd:CouponCommand.Issue):CouponInfo.Coupon{
        return CouponInfo.Coupon(1L, UUID.randomUUID().toString(), LocalDateTime.now(), false)
    }
}