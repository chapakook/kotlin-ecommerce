package kr.hhplus.be.server.support

import java.util.concurrent.TimeUnit

interface RedisDistributedLock {
    fun lock(key: String, value: String, timeout: Long = 3000, timeUnit: TimeUnit = TimeUnit.MILLISECONDS): Boolean
    fun unlock(key: String, value: String): Boolean
}