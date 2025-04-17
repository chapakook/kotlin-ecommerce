package kr.hhplus.be.server.domain.popularity

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
class Popularity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val popularityId: Long,
    val productId: Long,
    val productName: String,
    val rank: Int,
    val totalOrder: Int,
    val updateMills: Long,
)