package kr.hhplus.be.server.support.lock

import kr.hhplus.be.server.support.ErrorCode.UNABLE_ACQUIRE_LOCK
import kr.hhplus.be.server.support.annotation.DistributedLock
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.reflect.MethodSignature
import org.redisson.api.RedissonClient
import org.springframework.core.Ordered
import org.springframework.core.annotation.Order
import org.springframework.expression.spel.standard.SpelExpressionParser
import org.springframework.expression.spel.support.StandardEvaluationContext
import org.springframework.stereotype.Component

@Order(Ordered.HIGHEST_PRECEDENCE)
@Aspect
@Component
class RedissonDistributedLockAspect(
    private val redissonClient: RedissonClient,
) {
    @Around("@annotation(kr.hhplus.be.server.support.annotation.DistributedLock)")
    fun around(joinPoint: ProceedingJoinPoint): Any? {
        val method = (joinPoint.signature as MethodSignature).method
        val annotation = method.getAnnotation(DistributedLock::class.java)
        val parser = SpelExpressionParser()
        val context = StandardEvaluationContext().apply {
            method.parameters.forEachIndexed { idx, param -> setVariable(param.name, joinPoint.args[idx]) }
        }
        val key = parser
            .parseExpression(annotation.key)
            .getValue(context, String::class.java)
            ?: annotation.key
        val lock = redissonClient.getFairLock(key)
        return if (lock.tryLock(120000, annotation.timeout, annotation.timeUnit)) {
            try {
                joinPoint.proceed()
            } finally {
                if (lock.isHeldByCurrentThread) {
                    lock.unlock()
                }
            }
        } else {
            throw IllegalStateException(UNABLE_ACQUIRE_LOCK.message)
        }
    }
}