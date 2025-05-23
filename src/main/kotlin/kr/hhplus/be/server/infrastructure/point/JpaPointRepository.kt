package kr.hhplus.be.server.infrastructure.point

import kr.hhplus.be.server.domain.point.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface JpaPointRepository : JpaRepository<Point, Long> {
    fun findByUserId(userId: Long): Point?
    fun save(point: Point): Point
}