package kr.hhplus.be.server.domain.product

import io.mockk.mockk
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
    inner class Find {
//        @Test
//        fun `happy - 상품을 가져온다`() {
//            // given
//            val productId = 1L
//            val cmd = ProductCommand.Find(productId)
//            val fake = ProductInfo.ProductInfo(1L, "Product", 100L, 100)
//            every { productRepository.findProductById(any()) } returns fake
//            // when
//            val product = productService.find(cmd)
//            // then
//            assertThat(product).isEqualTo(fake)
//        }
    }

    @Nested
    inner class Reduce {
//        @Test
//        fun `happy - 주문 수량보다 재고가 많을때 재고를 차감한다`() {
//            // given
//            val productId = 1L
//            val quantity = 2
//            val reduceCmd = ProductCommand.Reduce(productId, quantity)
//            val base = ProductInfo.ProductInfo(1L, "Product", 100L, 100)
//            val fake = ProductInfo.ProductInfo(1L, "Product", 100L, 100 - quantity)
//            every { productRepository.findProductById(any()) } returns base
//            every { productRepository.update() } returns fake
//
//            // when
//            val result = productService.reduce(reduceCmd)
//            // then
//            assertThat(result)
//                .extracting("productId", "stock")
//                .containsExactly(productId, 100 - quantity)
//        }

//        @Test
//        fun `happy - 주문 수량과 재고 같을때 재고를 차감한다`() {
//            // given
//            val productId = 1L
//            val quantity = 100
//            val reduceCmd = ProductCommand.Reduce(productId, quantity)
//            val base = ProductInfo.ProductInfo(1L, "Product", 100L, 100)
//            val fake = ProductInfo.ProductInfo(1L, "Product", 100L, 100 - quantity)
//            every { productRepository.findProductById(any()) } returns base
//            every { productRepository.update() } returns fake
//
//            // when
//            val result = productService.reduce(reduceCmd)
//            // then
//            assertThat(result)
//                .extracting("productId", "stock")
//                .containsExactly(productId, 100 - quantity)
//        }

//        @Test
//        fun `bad - 재고가 주문수량보다 적을때 실패한다`() {
//            // given
//            val productId = 1L
//            val quantity = 200
//            val reduceCmd = ProductCommand.Reduce(productId, quantity)
//            val base = ProductInfo.ProductInfo(1L, "Product", 100L, 100)
//            every { productRepository.findProductById(any()) } returns base
//            // when & then
//            assertThatThrownBy { productService.reduce(reduceCmd) }
//                .isInstanceOf(IllegalArgumentException::class.java)
//        }
    }

//    @Nested
//    inner class Ranks {
//        @Test
//        fun `happy - rankCmd 이용 getRank 요청시 정상적으로 인기상품을 가져온다`() {
//            // given
//            val fakeRank = emptyList<ProductInfo.Rank>()
//            every { productRepository.findRank() } returns fakeRank
//            // when
//            val rank = productService.ranks()
//            // then
//            assertThat(rank).isEqualTo(fakeRank)
//        }
//    }
}