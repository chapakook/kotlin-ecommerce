package kr.hhplus.be.server.infrastructure.product

import jakarta.persistence.*
import kr.hhplus.be.server.domain.product.Product

@Entity
@Table(name = "products ")
class ProductEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val price: Long,
) {
    companion object {
        fun of(product: Product): ProductEntity = with(product) { ProductEntity(productId, name, price) }
    }

    fun to(): Product = Product(productId, name, price)
}