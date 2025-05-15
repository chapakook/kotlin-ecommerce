package kr.hhplus.be.server.infrastructure.stock

import kr.hhplus.be.server.domain.stock.Stock
import kr.hhplus.be.server.domain.stock.StockRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class StockRepositoryImpl(
    private val jpaStockRepository: JpaStockRepository,
) : StockRepository {
    override fun findByProductId(productId: Long): Stock? = jpaStockRepository.findByProductId(productId)

    @Transactional
    override fun save(stock: Stock): Stock = jpaStockRepository.save(stock)

    @Transactional
    override fun findByProductIdWithPessimisticLock(productId: Long): Stock? =
        jpaStockRepository.findByProductIdWithPessimisticLock(productId)
}