package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.domain.coupon.CouponService
import kr.hhplus.be.server.interfaces.coupon.CouponResponse.IssueResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupon")
class CouponController(
    private val couponService: CouponService,
) {
    @PostMapping("/issue")
    fun issue(@RequestBody req: CouponRequest.Issue): IssueResult? {
        val coupon = couponService.issue(req.toCouponCmdIssue())
        return if (coupon != null) IssueResult.of(coupon) else null
    }
}