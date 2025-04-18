package kr.hhplus.be.server.domain.stock

class StockInfo {
    class StockInfo(
        val stockId: Long,
        val productId: Long,
        val quantity: Int,
        val version: Int,
    ) {
        companion object {
            fun of(stock: Stock): StockInfo =
                with(stock) { StockInfo(stock.productId, stock.productId, stock.quantity, stock.version) }
        }
    }
}
