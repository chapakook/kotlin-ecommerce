package kr.hhplus.be.server.support.annotation

import java.util.concurrent.TimeUnit

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class DistributedLock(
    val key: String,
    val timeout: Long = 30000,
    val timeUnit: TimeUnit = TimeUnit.MILLISECONDS
)