package kr.hhplus.be.server.domain.product

import jakarta.persistence.*

@Entity
@Table(name = "products ")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long,
    val name: String,
    val price: Long,
)