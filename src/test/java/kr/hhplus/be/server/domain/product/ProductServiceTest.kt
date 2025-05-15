package kr.hhplus.be.server.domain.product

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

class ProductServiceTest {
    private val productRepository = mockk<ProductRepository>()
    private val productCacheRepository = mockk<ProductCacheRepository>()
    private val productRankingRepository = mockk<ProductRankingRepository>()
    private val productService = ProductService(productRepository, productCacheRepository, productRankingRepository)

    @Nested
    inner class Find {
        @Test
        fun `happy - 상품을 가져온다`() {
            // given
            val productId = 1L
            val cmd = ProductCommand.Find(productId)
            val product = ProductInfo.ProductInfo(1L, "Product", 100L)
            every { productCacheRepository.findByProductId(any()) } returns product
            // when
            val result = productService.find(cmd)
            // then
            assertThat(result.productId).isEqualTo(product.productId)
            assertThat(result.name).isEqualTo(product.name)
            assertThat(result.price).isEqualTo(product.price)
        }
    }

    @Nested
    inner class Rank {
        @Test
        fun `happy - 랭크가 조회된다`() {
            // given
            val expected = listOf(
                ProductRank(1L, "Product1", 100.0),
                ProductRank(2L, "Product2", 200.0),
                ProductRank(3L, "Product3", 300.0),
            )
            every { productRankingRepository.findAllByDays(any()) } returns expected
            // when
            val result = productService.rank()
            // then
            assertThat(result).hasSize(expected.size)
        }
    }
}