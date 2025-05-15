package kr.hhplus.be.server.domain.product

import java.time.Duration

interface ProductRankingRepository {
    fun findByProductId(productId: Long): ProductRank?
    fun findAllByDays(days: Long): List<ProductRank>
    fun increaseProductRank(productId: Long, scoreDelta: Double, timeout: Duration = Duration.ofDays(3))
}