package kr.hhplus.be.server.domain.stock

class StockCommand {
    class Find(val productId: Long)
    class Deduct(val productId: Long, val quantity: Int)
}
