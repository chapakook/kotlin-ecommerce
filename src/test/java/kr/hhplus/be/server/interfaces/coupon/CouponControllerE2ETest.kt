package kr.hhplus.be.server.interfaces.coupon

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CouponControllerE2ETest {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `POST - 선착순 쿠폰 발급 E2E 테스트`() {
        // given
        val userId = 1L
        val couponId = 1L
        val baseUri = "http://localhost:$port/coupon/issue"
        val req = CouponRequest.Issue(userId, couponId)
        // when
        val resp: ResponseEntity<CouponResponse.IssueV1> =
            restTemplate.postForEntity(baseUri, req, CouponResponse.IssueV1::class.java)
        // then
        assertThat(resp.statusCode).isEqualTo(HttpStatus.OK)
    }
}