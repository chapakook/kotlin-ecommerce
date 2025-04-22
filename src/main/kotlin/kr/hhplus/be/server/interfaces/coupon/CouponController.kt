package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.application.coupon.CouponFacade
import kr.hhplus.be.server.interfaces.coupon.CouponRequest.Issue
import kr.hhplus.be.server.interfaces.coupon.CouponResponse.IssueV1
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupon")
class CouponController(
    private val couponFacade: CouponFacade,
) {
    @PostMapping("/issue")
    fun issue(@RequestBody req: Issue): IssueV1 = IssueV1.of(couponFacade.issue(req.toCriteria()))
}