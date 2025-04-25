package kr.hhplus.be.server.interfaces.product

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerE2ETest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    private lateinit var restTemplate: TestRestTemplate

    @Test
    fun `GET - 상품조회 E2E 테스트`() {
        // given
        val baseUrl = ""
        // when
        // then
    }

    @Test
    fun `GET - 상위상품조회 E2E 테스트`() {
        // given
        val baseUrl = ""
        // when
        // then
    }
}