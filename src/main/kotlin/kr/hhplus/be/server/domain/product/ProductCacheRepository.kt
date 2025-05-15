package kr.hhplus.be.server.domain.product

interface ProductCacheRepository {
    fun set(productId: Long, name: String, price: Long)
    fun findByProductId(productId: Long): ProductInfo.ProductInfo?
    fun getNameByProductId(productId: String): String?
}