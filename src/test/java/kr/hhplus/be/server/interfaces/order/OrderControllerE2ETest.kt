package kr.hhplus.be.server.interfaces.order

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerE2ETest {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `POST - 주문생성 E2E 테스트`() {
        // given
        val userId = 1L
        val productId = 1L
        val quantity = 1
        val baseUrl = "http://localhost:$port/order"
        val req = OrderRequest.Order(userId, productId, quantity, null)
        // when
        val resp = restTemplate.postForEntity(baseUrl, req, OrderResponse.OrderV1::class.java)
        // then
        assertThat(resp.statusCode).isEqualTo(HttpStatus.OK)
    }
}