package kr.hhplus.be.server.interfaces.product

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [ProductController::class])
class ProductInfoControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Nested
    inner class ProductInfo {
        @Test
        fun `happy - 정상적인 상품아이디로 조회시 조회되어야한다`(){
            // given
            val productId = 1L
            // when & then
            mockMvc.perform(MockMvcRequestBuilders.get("/product/$productId"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }

    @Nested
    inner class Rank {
        @Test
        fun `happy - 랭크 조회시 상위 5개 랭크가 조회된다`(){
            // given
            // when & then
            mockMvc.perform(MockMvcRequestBuilders.get("/product/rank"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }
}