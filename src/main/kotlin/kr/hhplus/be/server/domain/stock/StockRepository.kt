package kr.hhplus.be.server.domain.stock

import org.springframework.stereotype.Repository

@Repository
interface StockRepository {
    fun findByProductId(productId: Long): Stock?
    fun save(stock: Stock): Stock
    fun findByProductIdWithPessimisticLock(productId: Long): Stock?
}