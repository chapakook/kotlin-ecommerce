package kr.hhplus.be.server.interfaces.product

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerE2ETest(
    @Autowired private val restTemplate: TestRestTemplate,
    @Autowired private val objectMapper: ObjectMapper
) {
    @LocalServerPort
    private var port: Int = 0


    @Test
    fun `GET - 상품조회 E2E 테스트`() {
        // given
        val productId = 1L
        val baseUrl = "http://localhost:$port/products/$productId"
        // when
        val resp: ResponseEntity<ProductResponse.ProductV1> =
            restTemplate.getForEntity(baseUrl, ProductResponse.ProductV1::class.java)
        // then
        assertThat(resp.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(resp.body?.productId).isEqualTo(productId)
    }

    @Test
    fun `GET - 상위상품조회 E2E 테스트`() {
        // given
        val baseUrl = "http://localhost:$port/products/rank"
        // when
        val resp: ResponseEntity<String> = restTemplate.getForEntity(baseUrl, String::class.java)
        val results: List<ProductResponse.RankV1> =
            objectMapper.readValue(resp.body, object : TypeReference<List<ProductResponse.RankV1>>() {})
        // then
        assertThat(resp.statusCode).isEqualTo(HttpStatus.OK)
        assertThat(results).isNotEmpty
        assertThat(results).hasSize(10)
    }
}