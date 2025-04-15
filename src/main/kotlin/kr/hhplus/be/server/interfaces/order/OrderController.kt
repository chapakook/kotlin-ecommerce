package kr.hhplus.be.server.interfaces.order

import kr.hhplus.be.server.domain.order.OrderService
import kr.hhplus.be.server.interfaces.order.OrderRequest.Order
import kr.hhplus.be.server.interfaces.order.OrderResponse.OrderResult
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderService: OrderService,
) {
    @PostMapping("")
    fun order(@RequestBody req: Order): OrderResult {
        val order = orderService.order(req.to())
        return OrderResult.of(order)
    }
}