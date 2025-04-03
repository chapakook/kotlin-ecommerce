package kr.hhplus.be.server.discount

import jakarta.validation.Valid
import kr.hhplus.be.server.swagger.DiscountApi
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/discounts")
class DiscountController: DiscountApi {

    @PostMapping("/coupon/issue")
    override fun issueCoupon(@RequestBody @Valid request: IssueCouponRequest): Discount {
        return Discount(
            discountId = 1L,
            productId = 2L,
            discountType = DiscountType.FIXED,
            value = 3000,
            expirationDate = LocalDateTime.now().plusDays(7)
        )
    }
}