package kr.hhplus.be.server.domain.popularity

import jakarta.persistence.*

@Entity
@Table(name = "popularises")
class Popularity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val popularityId: Long,
    @Column(nullable = false)
    val productId: Long,
    @Column(nullable = false)
    val productName: String,
    @Column(name = "`rank`", nullable = false)
    val rank: Int,
    @Column(nullable = false)
    val totalOrder: Int,
    @Column(nullable = false)
    val updateMills: Long,
)