package kr.hhplus.be.server.domain.stock

import org.springframework.stereotype.Repository

@Repository
interface StockRepository {
    fun findStockById(id: Long): Stock?
    fun save(point: Stock): Stock
}