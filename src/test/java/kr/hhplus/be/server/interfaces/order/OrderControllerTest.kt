package kr.hhplus.be.server.interfaces.order

import com.fasterxml.jackson.databind.ObjectMapper
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import kr.hhplus.be.server.application.order.OrderFacade
import kr.hhplus.be.server.application.order.OrderResult
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

@WebMvcTest(OrderController::class)
class OrderControllerTest {
    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    @MockkBean
    private lateinit var orderFacade: OrderFacade

    @Nested
    inner class Order {
        @Test
        fun `happy - 정상적인 주문 요청시 주문되어야한다`() {
            // given
            val productId = 1L
            val price = 100L
            val quantity = 2
            val userId = 2L
            val orderId = 3L
            val req = OrderRequest.Order(userId, productId, quantity, null)
            val result =
                OrderResult.Order(
                    orderId,
                    userId,
                    productId,
                    quantity,
                    price * quantity,
                    LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
                )
            // when
            every { orderFacade.order(any()) } returns result
            // then
            mockMvc.perform(
                MockMvcRequestBuilders.post("/order")
                    .content(objectMapper.writeValueAsString(req))
                    .contentType(MediaType.APPLICATION_JSON)
            )
                .andExpect(MockMvcResultMatchers.status().isOk())
        }
    }
}