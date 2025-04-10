package kr.hhplus.be.server.domain.coupon

import org.springframework.stereotype.Service

@Service
class CouponService {
    fun useCoupon(cmd:CouponCommand.Use): CouponInfo.Coupon{
        return CouponInfo.Coupon()
    }
}