package kr.hhplus.be.server.domain.popularity

import jakarta.persistence.*

@Entity
@Table(name = "popularities")
class Popularity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val popularityId: Long,
    val productId: Long,
    val productName: String,
    @Column(name = "`rank`")
    val rank: Int,
    val totalOrder: Int,
    val updateMills: Long,
)