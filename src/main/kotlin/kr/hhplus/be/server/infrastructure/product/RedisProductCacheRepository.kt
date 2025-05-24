package kr.hhplus.be.server.infrastructure.product

import kr.hhplus.be.server.domain.product.ProductCacheRepository
import kr.hhplus.be.server.domain.product.ProductInfo
import kr.hhplus.be.server.support.RedisPrefix.CACHE_PRODUCTS
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration

@Repository
class RedisProductCacheRepository(
    private val redisTemplate: RedisTemplate<String, String>
) : ProductCacheRepository {
    override fun set(productId: Long, name: String, price: Long) {
        val key = "${CACHE_PRODUCTS.prefix}:${productId}"
        redisTemplate.opsForHash<String, String>().putAll(
            key,
            mapOf(
                "name" to name,
                "price" to price.toString()
            )
        )
        redisTemplate.expire(key, Duration.ofHours(1))
    }

    override fun findByProductId(productId: Long): ProductInfo.ProductInfo? =
        redisTemplate.opsForHash<String, String>()
            .entries("${CACHE_PRODUCTS.prefix}:${productId}").let {
                val name = it["name"] ?: return@let null
                val price = it["price"]?.toLongOrNull() ?: return@let null
                ProductInfo.ProductInfo(productId, name, price)
            }

    override fun getNameByProductId(productId: String): String? = redisTemplate.opsForHash<String, String>()
        .get("${CACHE_PRODUCTS.prefix}:${productId}", "name")
}