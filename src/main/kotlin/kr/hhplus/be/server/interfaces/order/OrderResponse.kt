package kr.hhplus.be.server.interfaces.order

import kr.hhplus.be.server.application.order.OrderResult

class OrderResponse {
    class OrderV1() {
        companion object {
            fun of(order: OrderResult.Order): OrderV1 = with(order) { OrderV1() }
        }
    }
}