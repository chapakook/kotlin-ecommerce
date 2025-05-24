package kr.hhplus.be.server.infrastructure.couponevent

import kr.hhplus.be.server.domain.couponevent.CouponEventCacheRepository
import kr.hhplus.be.server.support.RedisPrefix.CACHE_COUPON_EVENTS
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisCouponEventCacheRepository(
    private val redisTemplate: RedisTemplate<String, String>
) : CouponEventCacheRepository {
    override fun set(couponEventId: Long, userId: Long) {
        redisTemplate.opsForSet().add("${CACHE_COUPON_EVENTS.prefix}:$couponEventId", userId.toString())
    }

    override fun existsByUserIdAndCouponEventId(userId: Long, couponEventId: Long): Boolean =
        redisTemplate.opsForSet().isMember("${CACHE_COUPON_EVENTS.prefix}:$couponEventId", userId.toString()) == true
}