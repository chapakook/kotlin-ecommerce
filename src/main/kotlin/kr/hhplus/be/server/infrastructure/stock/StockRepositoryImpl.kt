package kr.hhplus.be.server.infrastructure.stock

import kr.hhplus.be.server.domain.stock.Stock
import kr.hhplus.be.server.domain.stock.StockRepository
import org.springframework.stereotype.Repository

@Repository
class StockRepositoryImpl(
    private val stockJPARepository: StockJPARepository,
) : StockRepository {
    override fun findProductStockById(id: Long): Stock? = stockJPARepository.findProductStockById(id)
    override fun save(point: Stock): Stock = stockJPARepository.save(point)
}