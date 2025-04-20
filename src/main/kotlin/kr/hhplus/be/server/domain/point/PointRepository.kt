package kr.hhplus.be.server.domain.point

import org.springframework.stereotype.Repository

@Repository
interface PointRepository {
    fun findPointById(id: Long): Point?
    fun save(point: Point): Point
}