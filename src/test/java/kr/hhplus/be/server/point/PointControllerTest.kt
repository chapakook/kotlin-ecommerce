package kr.hhplus.be.server.point

import com.fasterxml.jackson.databind.ObjectMapper
import kr.hhplus.be.server.order.OrderRequest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [PointController::class])
class PointControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Test
    fun `성공 - 포인트를 조회한다` (){
        // given
        val userId: Long = 1L

        // when & then
        mockMvc.perform(MockMvcRequestBuilders.get("/points/$userId"))
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
    }

    fun `성공 - 포인트를 충전하다` (){
        // given
        val userId: Long = 1L
        val amount: Int = 1000

        // when & then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("/points/$userId/charge")
                .content(objectMapper.writeValueAsString(amount))
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andDo(MockMvcResultHandlers.print())
            .andExpect(MockMvcResultMatchers.status().isOk())
    }
}