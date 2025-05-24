package kr.hhplus.be.server.support.lock

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@DataRedisTest
@Import(value = [RedisSpinLock::class])
class RedisSpinLockTest(
    @Autowired private val redisSpinLock: RedisSpinLock,
) {
    @Test
    fun `happy - lock unlock에 성공한다`() {
        // given
        val key = "lock:coupon:1"
        val value = "1"
        // then
        val locked = redisSpinLock.lock(key, value)
        val unlocked = redisSpinLock.unlock(key, value)
        // then
        assertThat(locked).isTrue()
        assertThat(unlocked).isTrue()
    }

    @Test
    fun `happy - lock이 걸려있다면 lock에 실패한다`() {
        // given
        val key = "lock:coupon:12"
        val value1 = "12"
        val value2 = "123"
        // when
        val locked1 = redisSpinLock.lock(key, value1)
        val locked2 = redisSpinLock.lock(key, value2, 1000)
        val unlocked1 = redisSpinLock.unlock(key, value1)
        // then
        assertThat(locked1).isTrue()
        assertThat(locked2).isFalse()
        assertThat(unlocked1).isTrue()
    }

    @Test
    fun `happy - lock이 선점되어 있으면 대기하고있다가 성공한다`() {
        // given
        val key = "lock:coupon:123456"
        val value1 = "123456"
        val value2 = "1234567"
        val executor = Executors.newFixedThreadPool(2)
        val latch = CountDownLatch(1)
        // when
        val future1 = executor.submit<Boolean> {
            val locked = redisSpinLock.lock(key, value1, 300000)
            latch.countDown()
            Thread.sleep(1000)
            redisSpinLock.unlock(key, value1)
            locked
        }
        val future2 = executor.submit<Boolean> {
            latch.await()
            val locked = redisSpinLock.lock(key, value2)
            redisSpinLock.unlock(key, value2)
            locked
        }
        executor.shutdown()
        // then
        assertThat(future1.get()).isTrue()
        assertThat(future2.get()).isTrue()
    }
}