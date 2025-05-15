package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.application.coupon.CouponCriteria
import kr.hhplus.be.server.application.coupon.CouponFacade
import kr.hhplus.be.server.interfaces.coupon.CouponRequest.Issue
import kr.hhplus.be.server.interfaces.coupon.CouponResponse.IssueV1
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/coupon")
class CouponController(
    private val couponFacade: CouponFacade,
) {
    @PostMapping("/issue")
    fun issue(@RequestBody req: Issue): IssueV1 = IssueV1.of(couponFacade.issue(req.toCriteria()))

    @PostMapping("/{couponEventId}/enqueue")
    fun enqueue(@PathVariable couponEventId: Long, @RequestParam userId: Long): Boolean = couponFacade.enqueue(
        CouponCriteria.Enqueue(couponEventId, userId)
    )
}