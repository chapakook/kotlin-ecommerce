package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class CouponService {
    fun use(cmd:CouponCommand.Use): CouponInfo.Coupon{
        return CouponInfo.Coupon(1L, UUID.randomUUID().toString(), LocalDateTime.now(), true)
    }
    fun issue(cmd:CouponCommand.Issue):CouponInfo.Coupon{
        return CouponInfo.Coupon(1L, UUID.randomUUID().toString(), LocalDateTime.now(), false)
    }
}