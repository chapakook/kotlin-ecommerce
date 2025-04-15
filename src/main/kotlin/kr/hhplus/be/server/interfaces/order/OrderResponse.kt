package kr.hhplus.be.server.interfaces.order

import kr.hhplus.be.server.domain.order.OrderInfo.Order

class OrderResponse {
    class OrderResult() {
        companion object {
            fun of(order: Order): OrderResult = with(order) { OrderResult() }
        }
    }
}