package kr.hhplus.be.server.infrastructure.point

import jakarta.persistence.*
import kr.hhplus.be.server.domain.point.Point

@Entity
@Table(name = "points")
class PointEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val pointId: Long,
    @Column(nullable = false)
    val userId: Long,
    @Column(nullable = false)
    val balance: Long,
    @Column(nullable = false)
    val updateMillis: Long,
) {
    companion object {
        fun of(point: Point): PointEntity = with(point) { PointEntity(pointId, userId, balance, updateMillis) }
    }

    fun to(): Point = Point(pointId, userId, balance, updateMillis)
}