package kr.hhplus.be.server.interfaces.coupon

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kr.hhplus.be.server.application.coupon.CouponFacade
import kr.hhplus.be.server.application.coupon.CouponResult
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.time.LocalDateTime
import java.time.ZoneOffset

@WebMvcTest(CouponController::class)
class CouponControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var couponFacade: CouponFacade

    @Nested
    inner class Issue {
        @Test
        fun `happy - 정상적인 쿠폰 발급 요청시 쿠폰발급되어야한다`() {
            // given
            val couponId = 2L
            val userId = 1L
            val req = CouponRequest.Issue(userId, couponId)
            val coupon = CouponResult.Issue(
                couponId,
                LocalDateTime.now().plusDays(7).toInstant(ZoneOffset.UTC).toEpochMilli(),
            )
            // when
            every { couponFacade.issue(any()) } returns coupon
            // then
            mockMvc.perform(
                MockMvcRequestBuilders.post("/coupon/issue")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req))
            )
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }

}