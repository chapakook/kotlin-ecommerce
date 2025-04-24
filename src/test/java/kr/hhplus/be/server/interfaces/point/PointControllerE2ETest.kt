package kr.hhplus.be.server.interfaces.point

import kr.hhplus.be.server.interfaces.point.PointResponse.ChargeV1
import kr.hhplus.be.server.interfaces.point.PointResponse.PointV1
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PointControllerE2ETest {

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `GET - 포인트 조회 E2E 테스트`() {
        // given
        val userId = 1L
        val baseUrl = "http://localhost:$port/point/$userId"
        // when
        val resp: ResponseEntity<PointV1> = restTemplate.getForEntity(baseUrl, PointV1::class.java)
        // then
        assertThat(resp.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(resp.body?.balance).isEqualTo(1253500L)
    }

    @Test
    fun `PATCH - 포인트 충전 E2E 테스트`() {
        // given
        val userId = 1L
        val baseUrl = "http://localhost:$port/point/$userId/charge"
        val req = PointRequest.Charge(1000L)
        // when
        val resp = restTemplate.patchForObject(baseUrl, req, ChargeV1::class.java)
        // then
        assertThat(resp.balance).isEqualTo(1253500L + req.amount)
    }
}