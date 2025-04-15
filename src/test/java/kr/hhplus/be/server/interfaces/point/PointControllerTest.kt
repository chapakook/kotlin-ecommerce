package kr.hhplus.be.server.interfaces.point

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kr.hhplus.be.server.domain.point.PointInfo.Info
import kr.hhplus.be.server.domain.point.PointService
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.time.ZoneOffset
import kotlin.random.Random

@WebMvcTest(PointController::class)
class PointControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var pointService: PointService

    private fun createPointInfo(pointId: Long, userId: Long): Info = Info(
        pointId,
        userId,
        Random.nextLong(100L, Long.MAX_VALUE),
        LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    )

    @Nested
    inner class Point {
        @Test
        fun `happy - 정상 유저ID 이용 요청시 정상 응답함`() {
            // given
            val userId = 1L
            val pointInfo = createPointInfo(2L, userId)
            // when
            every { pointService.find(any()) } returns pointInfo
            // then
            mockMvc.perform(
                get("/point/$userId")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.balance").value(pointInfo.balance))
        }
    }

    @Nested
    inner class Charge {
        @Test
        fun `happy - 정상적인 포인트 충전 요청시 포인트 충전인된다`() {
            // given
            val userId = 1L
            val req = PointRequest.Charge(100L)
            val pointInfo = createPointInfo(2L, userId)
            // when
            every { pointService.charge(any()) } returns pointInfo
            // then
            mockMvc.perform(
                MockMvcRequestBuilders.patch("/point/$userId/charge")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req))
            )
                .andExpect(status().isOk())
        }
    }
}