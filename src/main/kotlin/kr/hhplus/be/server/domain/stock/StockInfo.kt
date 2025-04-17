package kr.hhplus.be.server.domain.stock

class StockInfo {
    class ProductStockInfo(
        val stockId: Long,
        val productId: Long,
        val quantity: Int,
        val version: Int,
    ) {
        companion object {
            fun of(stock: Stock): ProductStockInfo =
                with(stock) { ProductStockInfo(stock.productId, stock.productId, stock.quantity, stock.version) }
        }
    }
}
