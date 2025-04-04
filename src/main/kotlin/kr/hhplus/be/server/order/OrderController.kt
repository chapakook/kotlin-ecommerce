package kr.hhplus.be.server.order

import jakarta.validation.Valid
import kr.hhplus.be.server.swagger.OrderApi
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/orders")
class OrderController : OrderApi {

    @PostMapping("/place")
    override fun place(@RequestBody @Valid request: OrderRequest): OrderResponse {
        return OrderResponse(
            orderId = 1L,
            userId = 2L,
            totalPrice = 3000,
            orderStatus = OrderStatus.PENDING
        )
    }
}