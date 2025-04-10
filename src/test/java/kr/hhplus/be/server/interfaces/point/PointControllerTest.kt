package kr.hhplus.be.server.interfaces.point

import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Nested
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(controllers = [PointController::class])
class PointInfoControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Nested
    inner class PointInfo{
        @Test
        fun `happy - given when then point`(){
            // given
            val userId = 1L
            // when & then
            mockMvc.perform(MockMvcRequestBuilders.get("/point/$userId"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }

    @Nested
    inner class Charge{
        @Test
        fun `happy - 정상적인 포인트 충전 요청시 포인트 충전인된다`() {
            // given
            val req = PointRequest.Charge()
            // when & then
            mockMvc.perform(
                MockMvcRequestBuilders.patch("/point/charge")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req))
            )
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }

    @Nested
    inner class Use{
        @Test
        fun `happy - 정상적인 포인트 사용 요청시 포인트 사용되어야한다`() {
            // given
            val req = PointRequest.Use()
            // when & then
            mockMvc.perform(
                MockMvcRequestBuilders.patch("/point/use")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req))
            )
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }
}