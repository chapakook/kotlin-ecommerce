package kr.hhplus.be.server.infrastructure.stock

import kr.hhplus.be.server.domain.stock.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockJPARepository : JpaRepository<Stock, Long> {
    fun findByProductId(productId: Long): Stock?
    fun save(stock: Stock): Stock
}