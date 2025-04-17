package kr.hhplus.be.server.domain.stock

import kr.hhplus.be.server.domain.stock.StockInfo.ProductStockInfo
import kr.hhplus.be.server.support.ErrorCode.STOCK_NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class StockService(
    private val productStockRepository: StockRepository,
) {
    fun find(cmd: StockCommand.Find): ProductStockInfo =
        productStockRepository.findProductStockById(cmd.productId)?.let { stock: Stock ->
            ProductStockInfo.of(stock)
        } ?: throw NoSuchElementException(STOCK_NOT_FOUND.message)

    @Transactional
    fun deduct(cmd: StockCommand.Deduct): ProductStockInfo =
        productStockRepository.findProductStockById(cmd.productId)?.let { stock: Stock ->
            stock.deduct(cmd.quantity)
            ProductStockInfo.of(stock)
        } ?: throw NoSuchElementException(STOCK_NOT_FOUND.message)
}