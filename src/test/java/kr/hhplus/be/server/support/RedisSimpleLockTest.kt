package kr.hhplus.be.server.support

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.data.redis.DataRedisTest
import org.springframework.context.annotation.Import

@DataRedisTest
@Import(value = [RedisSimpleLock::class])
class RedisSimpleLockTest(
    @Autowired private val redisSimpleLock: RedisSimpleLock,
) {
    @Test
    fun `happy - lock unlock에 성공한다`() {
        // given
        val key = "lock:coupon:1"
        val value = "1"
        // then
        val locked = redisSimpleLock.lock(key, value)
        val unlocked = redisSimpleLock.unlock(key, value)
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
        val locked1 = redisSimpleLock.lock(key, value1)
        val locked2 = redisSimpleLock.lock(key, value2)
        val unlocked1 = redisSimpleLock.unlock(key, value1)
        // then
        assertThat(locked1).isTrue()
        assertThat(locked2).isFalse()
        assertThat(unlocked1).isTrue()
    }

    @Test
    fun `happy - unlock 후 lock이 가능해야한다`() {
        // given
        val key = "lock:coupon:1234"
        val value1 = "1234"
        val value2 = "12345"
        // when
        val locked1 = redisSimpleLock.lock(key, value1)
        val locked2 = redisSimpleLock.lock(key, value2)
        val unlocked1 = redisSimpleLock.unlock(key, value1)
        val locked3 = redisSimpleLock.lock(key, value2)
        val unlocked2 = redisSimpleLock.unlock(key, value2)
        // then
        assertThat(locked1).isTrue()
        assertThat(locked2).isFalse()
        assertThat(locked3).isTrue()
        assertThat(unlocked1).isTrue()
        assertThat(unlocked2).isTrue()
    }
}