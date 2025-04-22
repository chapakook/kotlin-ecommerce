package kr.hhplus.be.server.domain.stock

import kr.hhplus.be.server.domain.stock.StockInfo.StockInfo
import kr.hhplus.be.server.support.ErrorCode.STOCK_NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StockService(
    private val stockRepository: StockRepository,
) {
    fun find(cmd: StockCommand.Find): StockInfo =
        stockRepository.findProductStockByStockId(cmd.productId)?.let { stock: Stock ->
            StockInfo.of(stock)
        } ?: throw NoSuchElementException(STOCK_NOT_FOUND.message)

    @Transactional
    fun deduct(cmd: StockCommand.Deduct): StockInfo =
        stockRepository.findProductStockByStockId(cmd.productId)?.let { stock: Stock ->
            stock.deduct(cmd.quantity)
            StockInfo.of(stock)
        } ?: throw NoSuchElementException(STOCK_NOT_FOUND.message)
}