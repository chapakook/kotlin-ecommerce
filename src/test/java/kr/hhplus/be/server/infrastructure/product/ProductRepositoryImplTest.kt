package kr.hhplus.be.server.infrastructure.product

import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ProductRepositoryImplTest {

    private val mockProductJPARepository = mockk<ProductJPARepository>()
    private val productRepositoryImpl = ProductRepositoryImpl(mockProductJPARepository)

    @Test
    fun `happy - 상품 아이디로 조회 가능`() {
        // given
        val entity = ProductEntity(1L, "P1", 1000L)
        every { mockProductJPARepository.findProductById(1L) } returns entity
        // when
        val result = productRepositoryImpl.findProductById(1L)
        // then
        result?.let {
            assertThat(result.productId).isEqualTo(entity.productId)
            assertThat(result.name).isEqualTo(entity.name)
            assertThat(result.price).isEqualTo(entity.price)
        }
        verify(exactly = 1) { mockProductJPARepository.findProductById(1L) }
    }
}