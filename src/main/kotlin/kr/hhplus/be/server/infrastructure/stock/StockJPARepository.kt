package kr.hhplus.be.server.infrastructure.stock

import kr.hhplus.be.server.domain.stock.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockJPARepository : JpaRepository<Stock, Long> {
    fun findProductStockById(id: Long): Stock?
    fun save(point: Stock): Stock
}