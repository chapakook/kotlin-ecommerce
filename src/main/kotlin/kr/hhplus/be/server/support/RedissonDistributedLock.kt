package kr.hhplus.be.server.support

import org.redisson.api.RLock
import org.redisson.api.RedissonClient
import org.springframework.stereotype.Component
import java.util.concurrent.TimeUnit

@Component
class RedissonDistributedLock(
    private val redissonClient: RedissonClient
) : RedisDistributedLock {
    override fun lock(key: String, value: String, timeout: Long, timeUnit: TimeUnit): Boolean {
        val lock: RLock = redissonClient.getFairLock(key)
        return lock.tryLock(1500, timeout, timeUnit)
    }

    override fun unlock(key: String, value: String): Boolean {
        val lock: RLock = redissonClient.getFairLock(key)
        if (lock.isHeldByCurrentThread) {
            lock.unlock()
            return true
        }
        return false
    }
}