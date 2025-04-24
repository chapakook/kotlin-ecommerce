package kr.hhplus.be.server.domain.product

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test

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
        @Test
        fun `happy - 상품을 가져온다`() {
            // given
            val productId = 1L
            val cmd = ProductCommand.Find(productId)
            val product = Product(1L, "Product", 100L)
            every { productRepository.findByProductId(any()) } returns product
            // when
            val result = productService.find(cmd)
            // then
            assertThat(result.productId).isEqualTo(product.productId)
            assertThat(result.name).isEqualTo(product.name)
            assertThat(result.price).isEqualTo(product.price)
        }
    }
}