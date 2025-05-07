package kr.hhplus.be.server.domain.product

import jakarta.persistence.*

@Entity
@Table(name = "products ")
class Product(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val productId: Long,
    @Column(nullable = false)
    val name: String,
    @Column(nullable = false)
    val price: Long,
)