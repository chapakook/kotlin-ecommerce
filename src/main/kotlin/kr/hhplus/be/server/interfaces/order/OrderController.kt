package kr.hhplus.be.server.interfaces.order

import kr.hhplus.be.server.application.order.OrderFacade
import kr.hhplus.be.server.interfaces.order.OrderRequest.Order
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController(
    private val orderFacade: OrderFacade,
) {
    @PostMapping("")
    fun order(@RequestBody req: Order): OrderResponse.OrderV1 {
        val order = orderFacade.order(req.to())
        return OrderResponse.OrderV1.of(order)
    }
}