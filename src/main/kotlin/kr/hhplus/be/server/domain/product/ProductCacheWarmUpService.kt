package kr.hhplus.be.server.domain.product

import jakarta.annotation.PostConstruct
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class ProductCacheWarmUpService(
    private val productRepository: ProductRepository,
    private val productCacheRepository: ProductCacheRepository,
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @PostConstruct
    fun warmup() {
        val products = productRepository.findTop10()
        products.forEach { with(it) { productCacheRepository.set(productId, name, price) } }
        log.info("✅ Complete Redis product hash warm-up - ${products.size}")
    }
}