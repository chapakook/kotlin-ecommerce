package kr.hhplus.be.server.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested

class ProductServiceTest {
    private lateinit var productService: ProductService

    @BeforeEach
    fun setUp() {
        productService = ProductService()
    }

    @Nested
    inner class GetProduct {
        @Test
        fun `happy - getCmd 이용 getProduct 요청시 정상적으로 상품을 가져온다`() {
            // given
            val getCmd = ProductCommand.Get()
            val fakeProduct = ProductInfo.Product(1L,"Product",100L)
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
            val rankCmd = ProductCommand.Rank()
            val fakeRank = emptyList<ProductInfo.Rank>()
            // when
            val rank = productService.getRank(rankCmd)
            // then
            assertThat(rank).isEqualTo(fakeRank)
        }
    }

}