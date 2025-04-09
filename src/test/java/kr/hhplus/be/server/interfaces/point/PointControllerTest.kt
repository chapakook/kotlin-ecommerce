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
class PointControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc
    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @Nested
    inner class Point{
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
        fun `happy - given when then charge`() {
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
        fun `happy - given when use`() {
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