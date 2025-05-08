package kr.hhplus.be.server.support

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit


@DataRedisTest
@Import(value = [RedissonDistributedLock::class, RedissonConfig::class])
class RedissonDistributedLockTest(
    @Autowired private val redissonDistributedLock: RedissonDistributedLock,
) {
    @Test
    fun `happy - lock unlock에 성공한다`() {
        // given
        val key = "lock:coupon:1"
        val value = "1"
        // then
        val locked = redissonDistributedLock.lock(key, value)
        val unlocked = redissonDistributedLock.unlock(key, value)
        // then
        assertThat(locked).isTrue()
        assertThat(unlocked).isTrue()
    }

    @Test
    fun `happy - lock이 공정성을 보장한다`() {
        // given
        val key = "lock:coupon:12"
        val count = 5
        val executor = Executors.newFixedThreadPool(count)
        val latch = CountDownLatch(count)
        val results = mutableListOf<String>()
        // when
        repeat(count) {
            executor.submit {
                val value = it.toString()
                try {
                    if (redissonDistributedLock.lock(key, value, 5000)) {
                        synchronized(results) { results.add(value) }
                        Thread.sleep(200)
                        redissonDistributedLock.unlock(key, value)
                    }
                } catch (e: Exception) {
                    println(e)
                } finally {
                    latch.countDown()
                }
            }
            Thread.sleep(100)
        }
        latch.await(10000, TimeUnit.MILLISECONDS)
        executor.shutdown()
        // then
        assertThat(results).containsExactly("0", "1", "2", "3", "4")
    }
}