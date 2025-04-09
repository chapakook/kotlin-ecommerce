package kr.hhplus.be.server.interfaces.coupon

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/coupon")
class CouponController {
    @PostMapping("/issue")
    fun issue(@RequestBody req: CouponRequest.Issue): CouponResponse.Coupon {
        return CouponResponse.Coupon()
    }
}