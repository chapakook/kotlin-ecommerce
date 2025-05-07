package kr.hhplus.be.server.infrastructure.stock

import jakarta.persistence.LockModeType
import kr.hhplus.be.server.domain.stock.Stock
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Lock
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface StockJPARepository : JpaRepository<Stock, Long> {
    fun findByProductId(productId: Long): Stock?
    fun save(stock: Stock): Stock

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("SELECT s FROM Stock s WHERE s.productId = :productId")
    fun findByProductIdWithPessimisticLock(productId: Long): Stock?
}