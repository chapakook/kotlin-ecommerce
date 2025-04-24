package kr.hhplus.be.server.infrastructure.product

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kr.hhplus.be.server.domain.product.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductRepositoryImplTest {

    private val mockProductJPARepository = mockk<ProductJPARepository>()
    private val productRepositoryImpl = ProductRepositoryImpl(mockProductJPARepository)

    @Test
    fun `happy - 상품 아이디로 조회 가능`() {
        // given
        val product = Product(1L, "P1", 1000L)
        every { mockProductJPARepository.findByProductId(any()) } returns product
        // when
        val result = productRepositoryImpl.findByProductId(1L)
        // then
        result?.let {
            assertThat(result.productId).isEqualTo(product.productId)
            assertThat(result.name).isEqualTo(product.name)
            assertThat(result.price).isEqualTo(product.price)
        }
        verify(exactly = 1) { mockProductJPARepository.findByProductId(any()) }
    }
}