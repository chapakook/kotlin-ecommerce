package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.domain.coupon.CouponService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupon")
class CouponController (
    private val couponService: CouponService
) {
    @PostMapping("/issue")
    fun issue(@RequestBody req: CouponRequest.Issue): CouponResponse.Coupon {
        val coupon = couponService.issue(req.toCouponCmdIssue())
        return CouponResponse().ofCoupon(coupon)
    }
}