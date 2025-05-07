package kr.hhplus.be.server.domain.stock

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class StockServiceTest {
    private val stockRepository = mockk<StockRepository>()
    private val stockService = StockService(stockRepository)

    @Nested
    inner class Find {
        @Test
        fun `happy - 재고 정상조회된다`() {
            // given
            val productId = 1L
            val cmd = StockCommand.Find(productId)
            val stock = Stock(
                1L,
                productId,
                100,
                0
            )
            every { stockRepository.findByProductId(any()) } returns stock
            // when
            val result = stockService.find(cmd)
            // then
            assertThat(result.stockId).isEqualTo(stock.stockId)
            assertThat(result.productId).isEqualTo(stock.productId)
            assertThat(result.quantity).isEqualTo(stock.quantity)
            assertThat(result.version).isEqualTo(stock.version)
        }
    }
}