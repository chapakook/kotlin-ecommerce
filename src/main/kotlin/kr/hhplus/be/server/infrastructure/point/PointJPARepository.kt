package kr.hhplus.be.server.infrastructure.point

import kr.hhplus.be.server.domain.point.Point
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PointJPARepository : JpaRepository<Point, Long> {
    fun findPointById(id: Long): Point?
    fun save(point: Point): Point
}