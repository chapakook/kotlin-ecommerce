package kr.hhplus.be.server.domain.stock

import org.springframework.stereotype.Repository

@Repository
interface StockRepository {
    fun findByProductId(productId: Long): Stock?
    fun save(point: Stock): Stock
}