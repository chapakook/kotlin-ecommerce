package kr.hhplus.be.server.domain.product

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class ProductServiceTest {
    private lateinit var productService: ProductService
    private lateinit var productRepository: ProductRepository

    @BeforeEach
    fun setUp() {
        productRepository = mockk()
        productService = ProductService(productRepository)
    }

    @Nested
    inner class GetProduct {
        @Test
        fun `happy - getCmd 이용 getProduct 요청시 정상적으로 상품을 가져온다`() {
            // given
            val getCmd = ProductCommand.Get(1L)
            val fakeProduct = ProductInfo.Product(1L,"Product",100L)
            every { productRepository.findProductById(any()) } returns fakeProduct
            // when
            val product = productService.getProduct(getCmd)
            // then
            assertThat(product).isEqualTo(fakeProduct)
        }
    }

    @Nested
    inner class GetRank{
        @Test
        fun `happy - rankCmd 이용 getRank 요청시 정상적으로 인기상품을 가져온다`() {
            // given
            val fakeRank = emptyList<ProductInfo.Rank>()
            // when
            val rank = productService.getRank()
            // then
            assertThat(rank).isEqualTo(fakeRank)
        }
    }
}