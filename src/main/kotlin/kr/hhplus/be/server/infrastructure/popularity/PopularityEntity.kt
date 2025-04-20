package kr.hhplus.be.server.infrastructure.popularity

import jakarta.persistence.*
import kr.hhplus.be.server.domain.popularity.Popularity

@Entity
@Table(name = "popularises")
class PopularityEntity(
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
) {
    companion object {
        fun of(popularity: Popularity): PopularityEntity =
            with(popularity) { PopularityEntity(popularityId, productId, productName, rank, totalOrder, updateMills) }

        fun ofList(popularises: List<Popularity>): List<PopularityEntity> = popularises.map { popularity ->
            with(popularity) { PopularityEntity(popularityId, productId, productName, rank, totalOrder, updateMills) }
        }
    }

    fun to(): Popularity = Popularity(popularityId, productId, productName, rank, totalOrder, updateMills)
}