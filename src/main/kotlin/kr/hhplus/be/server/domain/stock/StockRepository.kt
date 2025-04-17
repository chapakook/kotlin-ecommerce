package kr.hhplus.be.server.domain.stock

import org.springframework.stereotype.Repository

@Repository
interface StockRepository {
    fun findProductStockById(id: Long): Stock?
    fun save(point: Stock): Stock
}