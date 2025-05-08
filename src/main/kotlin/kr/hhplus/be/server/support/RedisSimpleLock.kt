package kr.hhplus.be.server.support

import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.script.DefaultRedisScript
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedisSimpleLock(
    private val redisTemplate: StringRedisTemplate
) : RedisDistributedLock {
    override fun lock(key: String, value: String, timeout: Long, timeUnit: TimeUnit): Boolean = redisTemplate
        .opsForValue()
        .setIfAbsent(key, value, timeout, timeUnit) ?: false

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