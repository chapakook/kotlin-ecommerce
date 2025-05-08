package kr.hhplus.be.server.interfaces.coupon

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

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

    @Test
    fun `POST - 선착순 쿠폰 100명 발급 E2E 테스트`() {
        // given
        val baseUri = "http://localhost:$port/coupon/issue"
        val userId = 1L
        val couponId = 1L
        val count = 100
        val executor = Executors.newFixedThreadPool(count)
        val latch = CountDownLatch(count)
        val results = mutableListOf<Boolean>()
        // when
        repeat(count) {
            executor.submit {
                try {
                    val req = CouponRequest.Issue(userId, couponId)
                    val resp: ResponseEntity<CouponResponse.IssueV1> = restTemplate
                        .postForEntity(
                            baseUri,
                            req,
                            CouponResponse.IssueV1::class.java
                        )
                    if (resp.statusCode == HttpStatus.OK) {
                        synchronized(results) { results.add(true) }
                    } else {
                        synchronized(results) { results.add(false) }
                    }
                } catch (e: Exception) {
                    synchronized(results) { results.add(false) }
                } finally {
                    latch.countDown()
                }
            }
        }
        latch.await(20000, TimeUnit.MILLISECONDS)
        executor.shutdown()
        // then
        assertThat(results).hasSize(count)
        assertThat(results).doesNotContain(false)
    }
}