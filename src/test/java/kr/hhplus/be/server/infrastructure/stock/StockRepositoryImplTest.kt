package kr.hhplus.be.server.infrastructure.stock

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.stock.Stock
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StockRepositoryImplTest {
    private val stockJPARepository = mockk<StockJPARepository>()
    private val stockRepository = StockRepositoryImpl(stockJPARepository)

    @Test
    fun `happy - 재고가 있을 때 재고를 반환`() {
        // given
        val stockId = 1L
        val productId = 2L
        val expectedStock = Stock(stockId, productId, 100, 0)
        every { stockJPARepository.findProductStockByStockId(stockId) } returns expectedStock
        // when
        val result = stockRepository.findProductStockByStockId(stockId)
        // then
        assertThat(result).isEqualTo(expectedStock)
        verify(exactly = 1) { stockJPARepository.findProductStockByStockId(stockId) }
    }

    @Test
    fun `happy - 재고가 없는 경우 null 반환`() {
        // given
        val stockId = 1L
        every { stockJPARepository.findProductStockByStockId(stockId) } returns null
        // when
        val result = stockRepository.findProductStockByStockId(stockId)
        // then
        assertThat(result).isNull()
        verify(exactly = 1) { stockJPARepository.findProductStockByStockId(stockId) }
    }

    @Test
    fun `happy - 저장하면 재고 반환`() {
        // given
        val stockId = 1L
        val productId = 2L
        val stock = Stock(stockId, productId, 100, 0)
        every { stockJPARepository.save(stock) } returns stock
        // when
        val result = stockRepository.save(stock)
        // then
        assertThat(result).isEqualTo(stock)
        verify(exactly = 1) { stockJPARepository.save(stock) }
    }
}