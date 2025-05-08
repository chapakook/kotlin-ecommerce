package kr.hhplus.be.server.domain.order

class OrderInfo {
    class OrderInfo(
        val orderId: Long,
        val userId: Long,
        val productId: Long,
        val quantity: Int,
        val totalAmount: Long,
        val createMillis: Long,
    ) {
        companion object {
            fun of(order: Order): OrderInfo =
                with(order) { OrderInfo(orderId, userId, productId, quantity, totalAmount, createMillis) }
        }
    }

    class OrderProductInfo(
        val rank: Int,
        val productId: Long,
        val productName: String,
        val totalOrder: Long,
    ) {
        companion object {
            fun ofList(list: List<OrderProduct>): List<OrderProductInfo> =
                list.sortedBy { it.totalOrder }.mapIndexed { idx, item ->
                    with(item) {
                        OrderProductInfo(
                            idx + 1,
                            productId,
                            productName,
                            totalOrder
                        )
                    }
                }
        }
    }
}