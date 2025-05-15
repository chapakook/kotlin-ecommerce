package kr.hhplus.be.server.application.aop

import kr.hhplus.be.server.domain.product.ProductRankingRepository
import kr.hhplus.be.server.support.RankingTarget
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import org.springframework.transaction.support.TransactionSynchronization
import org.springframework.transaction.support.TransactionSynchronizationManager

@Aspect
@Component
class IncreasesProductRankingAspect(
    private val productRankRepository: ProductRankingRepository
) {
    @AfterReturning("@annotation(kr.hhplus.be.server.support.annotation.IncreasesProductRanking)")
    fun afterReturning(joinPoint: JoinPoint) {
        if (!TransactionSynchronizationManager.isSynchronizationActive()) return
        TransactionSynchronizationManager.registerSynchronization(object : TransactionSynchronization {
            override fun afterCommit() {
                joinPoint.args
                    .filterIsInstance<RankingTarget>()
                    .forEach {
                        productRankRepository.increaseProductRank(
                            productId = it.productId,
                            scoreDelta = it.quantity.toDouble()
                        )
                    }
            }
        })
    }
}