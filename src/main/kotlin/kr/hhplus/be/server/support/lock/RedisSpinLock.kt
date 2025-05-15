package kr.hhplus.be.server.support.lock

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisSpinLock(
    private val redisTemplate: StringRedisTemplate
) : RedisDistributedLock {
    override fun lock(key: String, value: String, timeout: Long, timeUnit: TimeUnit): Boolean {
        val start = System.currentTimeMillis()
        while (System.currentTimeMillis() - start < timeout) {
            if (redisTemplate.opsForValue().setIfAbsent(key, value, timeout, timeUnit) ?: false) return true
            Thread.sleep(100)
        }
        return false
    }

    override fun unlock(key: String, value: String): Boolean = redisTemplate.execute(
        DefaultRedisScript(
            """
            if redis.call("get", KEYS[1]) == ARGV[1] then
                return redis.call("del", KEYS[1])
            else
                return 0
            end
        """.trimIndent(), Long::class.java
        ), listOf(key), value
    ) == 1L
}