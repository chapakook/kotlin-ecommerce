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
        val stock = Stock(stockId, productId, 100, 0)
        every { stockJPARepository.findByProductId(any()) } returns stock
        // when
        val result = stockRepository.findByProductId(stockId)
        // then
        result?.let {
            assertThat(result.stockId).isEqualTo(stock.stockId)
            assertThat(result.productId).isEqualTo(stock.productId)
            assertThat(result.quantity).isEqualTo(stock.quantity)
            assertThat(result.version).isEqualTo(stock.version)
        }
        verify(exactly = 1) { stockJPARepository.findByProductId(any()) }
    }

    @Test
    fun `happy - 재고가 없는 경우 null 반환`() {
        // given
        val stockId = 1L
        every { stockJPARepository.findByProductId(any()) } returns null
        // when
        val result = stockRepository.findByProductId(stockId)
        // then
        assertThat(result).isNull()
        verify(exactly = 1) { stockJPARepository.findByProductId(any()) }
    }

    @Test
    fun `happy - 저장하면 재고 반환`() {
        // given
        val stockId = 1L
        val productId = 2L
        val stock = Stock(stockId, productId, 100, 0)
        every { stockJPARepository.save(any()) } returns stock
        // when
        val result = stockRepository.save(stock)
        // then
        assertThat(result.stockId).isEqualTo(stock.stockId)
        assertThat(result.productId).isEqualTo(stock.productId)
        assertThat(result.quantity).isEqualTo(stock.quantity)
        assertThat(result.version).isEqualTo(stock.version)
        verify(exactly = 1) { stockJPARepository.save(any()) }
    }
}