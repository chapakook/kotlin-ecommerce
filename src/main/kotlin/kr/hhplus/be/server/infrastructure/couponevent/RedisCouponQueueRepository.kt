package kr.hhplus.be.server.infrastructure.couponevent

import kr.hhplus.be.server.domain.couponevent.CouponQueueRepository
import kr.hhplus.be.server.support.RedisPrefix.CACHE_QUEUE_COUPON_EVENTS
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisCouponQueueRepository(
    private val redisTemplate: RedisTemplate<String, String>,
) : CouponQueueRepository {
    val prefix = CACHE_QUEUE_COUPON_EVENTS.prefix

    override fun enqueue(coupontEventId: Long, userId: Long): Boolean = redisTemplate.opsForZSet().add(
        "$prefix:$coupontEventId",
        userId.toString(),
        System.nanoTime().toDouble(),
    ) == true

    override fun dequeue(coupontEventId: Long, count: Int): List<Long> = redisTemplate.opsForZSet()
        .range("$prefix:$coupontEventId", 0, (count - 1).toLong())
        ?.mapNotNull { it.toLongOrNull() }
        ?: emptyList()

    override fun remove(coupontEventId: Long, userId: Long) {
        redisTemplate.opsForZSet().remove("$prefix:$coupontEventId", userId.toString())
    }
}