package kr.hhplus.be.server.interfaces.order

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/order")
class OrderController {

    @PostMapping("")
    fun order(@RequestBody req: OrderRequest.Order): OrderResponse.Order {
        return OrderResponse.Order()
    }
}