package kr.hhplus.be.server.domain.product

interface ProductRepository {
    fun findTop10(): List<Product>
    fun findByProductId(productId: Long): Product?
    fun getProductOrderStatsByQuantity(): List<ProductOrder>
}