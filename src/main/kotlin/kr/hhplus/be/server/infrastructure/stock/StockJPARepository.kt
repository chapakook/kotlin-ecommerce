package kr.hhplus.be.server.infrastructure.stock

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface StockJPARepository : JpaRepository<StockEntity, Long> {
    fun findStockById(id: Long): StockEntity?
    fun save(stock: StockEntity): StockEntity
}