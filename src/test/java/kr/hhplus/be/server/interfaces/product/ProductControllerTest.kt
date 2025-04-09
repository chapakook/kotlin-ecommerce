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
class ProductControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Nested
    inner class Product {
        @Test
        fun `happy - given when then product`(){
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
        fun `happy - given when then rank`(){
            // given
            // when & then
            mockMvc.perform(MockMvcRequestBuilders.get("/product/rank"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }
}