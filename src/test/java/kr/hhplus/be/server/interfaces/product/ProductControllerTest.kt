package kr.hhplus.be.server.interfaces.product

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kr.hhplus.be.server.application.product.ProductFacade
import kr.hhplus.be.server.application.product.ProductResult.Product
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(ProductController::class)
class ProductControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockkBean
    private lateinit var productFacade: ProductFacade

    @Nested
    inner class Product {
        @Test
        fun `happy - 정상적인 상품아이디로 조회시 조회되어야한다`() {
            // given
            val productId = 1L
            val product = Product(productId, "test", 100L, 1)
            every { productFacade.find(any()) } returns product
            // when & then
            mockMvc.perform(MockMvcRequestBuilders.get("/product/$productId"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }

    @Nested
    inner class Rank {
        @Test
        fun `happy - 랭크 조회시 상위 5개 랭크가 조회된다`() {
            // given
            every { productFacade.ranks() } returns listOf()
            // when & then
            mockMvc.perform(MockMvcRequestBuilders.get("/product/ranks"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }
}