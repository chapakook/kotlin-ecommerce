package kr.hhplus.be.server.infrastructure.product

import kr.hhplus.be.server.domain.product.ProductRank
import kr.hhplus.be.server.domain.product.ProductRankingRepository
import kr.hhplus.be.server.support.RedisPrefix.CACHE_PRODUCTS
import kr.hhplus.be.server.support.RedisPrefix.CACHE_PRODUCT_RANK_DAILY
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository
import java.time.Duration
import java.time.LocalDate

@Repository
class RedisProductRankingRepository(
    private val redisTemplate: RedisTemplate<String, String>,
) : ProductRankingRepository {
    override fun findByProductId(productId: Long): ProductRank? = redisTemplate.opsForZSet()
        .score("${CACHE_PRODUCT_RANK_DAILY.prefix}:${LocalDate.now()}", productId.toString())
        ?.let {
            val name = redisTemplate.opsForHash<String, String>()
                .get("${CACHE_PRODUCTS.prefix}:${productId}", "name")
                ?: return@let null

            ProductRank(productId = productId, name = name, score = it)
        }

    override fun findAllByDays(days: Long): List<ProductRank> {
        val keys = (0 until days).map {
            "${CACHE_PRODUCT_RANK_DAILY.prefix}:${LocalDate.now().minusDays(it)}"
        }

        val rows = redisTemplate.opsForZSet().unionWithScores(keys.first(), keys.drop(1))
            ?: return emptyList()
        
        return rows.mapNotNull { tuple ->
            val productId = tuple.value?.toLongOrNull()
            val score = tuple.score
            if (productId != null && score != null) {
                val name = redisTemplate.opsForHash<String, String>().get("${CACHE_PRODUCTS.prefix}:$productId", "name")
                if (name != null) ProductRank(productId, name, score) else null
            } else null
        }
    }

    override fun increaseProductRank(productId: Long, scoreDelta: Double, timeout: Duration) {
        increaseScore(
            key = "${CACHE_PRODUCT_RANK_DAILY.prefix}:${LocalDate.now()}",
            member = productId.toString(),
            scoreDelta = scoreDelta,
            timeout = timeout
        )
    }

    private fun increaseScore(key: String, member: String, scoreDelta: Double, timeout: Duration) {
        redisTemplate.opsForZSet().incrementScore(key, member, scoreDelta)
        redisTemplate.expire(key, timeout)
    }
}