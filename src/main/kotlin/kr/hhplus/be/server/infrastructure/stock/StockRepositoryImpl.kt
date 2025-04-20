package kr.hhplus.be.server.infrastructure.stock

import kr.hhplus.be.server.domain.stock.Stock
import kr.hhplus.be.server.domain.stock.StockRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class StockRepositoryImpl(
    private val stockJPARepository: StockJPARepository,
) : StockRepository {
    override fun findStockById(id: Long): Stock? = stockJPARepository.findStockById(id)?.to()

    @Transactional
    override fun save(stock: Stock): Stock = stockJPARepository.save(StockEntity.of(stock)).to()
}