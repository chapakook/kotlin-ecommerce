package kr.hhplus.be.server.domain.stock

class StockCommand {
    data class Find(val productId: Long)
}
