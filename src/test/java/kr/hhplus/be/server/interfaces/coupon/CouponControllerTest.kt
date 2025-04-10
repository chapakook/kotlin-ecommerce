package kr.hhplus.be.server.interfaces.coupon

import com.fasterxml.jackson.databind.ObjectMapper
import kr.hhplus.be.server.interfaces.order.OrderRequest
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [CouponController::class])
class CouponInfoControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Nested
    inner class Issue{
        @Test
        fun `happy - 정상적인 쿠폰 발급 요청시 쿠폰발급되어야한다`() {
            // given
            val req = CouponRequest.Issue()
            // when & then
            mockMvc.perform(
                MockMvcRequestBuilders.post("/coupon/issue")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req))
            )
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }

}