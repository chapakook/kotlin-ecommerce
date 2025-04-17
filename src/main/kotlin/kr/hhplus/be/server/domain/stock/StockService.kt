package kr.hhplus.be.server.domain.stock

import kr.hhplus.be.server.domain.stock.StockCommand.Find
import kr.hhplus.be.server.domain.stock.StockInfo.ProductStockInfo
import kr.hhplus.be.server.support.ErrorCode.STOCK_NOT_FOUND
import org.springframework.stereotype.Service

@Service
class StockService(
    val productStockRepository: StockRepository,
) {
    fun find(findCmd: Find): ProductStockInfo =
        productStockRepository.findProductStockById(findCmd.productId)?.let { stock: Stock ->
            ProductStockInfo.of(stock)
        } ?: throw NoSuchElementException(STOCK_NOT_FOUND.message)
}