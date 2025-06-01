package kr.hhplus.be.server.interfaces.coupon

import kr.hhplus.be.server.application.coupon.CouponCriteria
import kr.hhplus.be.server.application.coupon.CouponFacade
import kr.hhplus.be.server.interfaces.coupon.CouponRequest.Issue
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/coupon")
class CouponController(
    private val couponFacade: CouponFacade,
    private val couponIssueRunner: CouponIssueRunner
) {
    @PostMapping("/issue")
    fun issue(@RequestBody req: Issue) {
        couponFacade.send(req.to())
    }

    @PostMapping("/{couponEventId}/enqueue")
    fun enqueue(@PathVariable couponEventId: Long, @RequestParam userId: Long): Boolean = couponFacade.enqueue(
        CouponCriteria.Enqueue(couponEventId, userId)
    )

    @PostMapping("/{eventId}/start")
    fun start(@PathVariable eventId: Long, @RequestParam cron: String): String {
        couponIssueRunner.start(eventId, cron)
        return "run"
    }

    @PostMapping("/{eventId}/stop")
    fun stop(@PathVariable eventId: Long): String {
        couponIssueRunner.stop(eventId)
        return "stop"
    }
}