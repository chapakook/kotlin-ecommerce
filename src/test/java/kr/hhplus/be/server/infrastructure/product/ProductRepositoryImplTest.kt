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
        val testProduct = Product(1L, "P1", 1000L)
        every { mockProductJPARepository.findProductByProductId(1L) } returns testProduct
        // when
        val result = productRepositoryImpl.findProductByProductId(1L)
        // then
        assertThat(result).isNotNull
        assertThat(result).isEqualTo(testProduct)
        verify(exactly = 1) { mockProductJPARepository.findProductByProductId(1L) }
    }
}