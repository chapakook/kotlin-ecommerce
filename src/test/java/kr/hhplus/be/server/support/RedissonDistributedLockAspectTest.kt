package kr.hhplus.be.server.support

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.stereotype.Service
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

@SpringBootTest
class RedissonDistributedLockAspectTest(
    @Autowired private val testService: TestService
) {
    @Test
    fun `happy - AOP lock 테스트`() {
        testService.lockedMethod(TestDto("lock:key"))
    }

    @Test
    fun `happy - AOP lock이 정상적동 한다`() {
        // given
        val key = "lock:coupon:1"
        val dto = TestDto(key)
        val count = 5
        val results = mutableListOf<Boolean>()
        val latch = CountDownLatch(count)
        val executor = Executors.newFixedThreadPool(count)
        // when
        repeat(count) {
            executor.submit {
                val result = testService.lockedMethod(dto)
                synchronized(results) { results.add(result) }
                latch.countDown()
            }
        }
        latch.await(20000, TimeUnit.MILLISECONDS)
        executor.shutdown()
        // then
        assertThat(results).hasSize(count)
    }
}

class TestDto(val key: String)

@Service
class TestService {
    @DistributedLock(key = "'lock:test:#dto.key'", timeout = 3000)
    fun lockedMethod(dto: TestDto): Boolean {
        Thread.sleep(200)
        return true
    }
}