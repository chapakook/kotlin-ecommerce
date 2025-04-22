package kr.hhplus.be.server.domain.point

import org.springframework.stereotype.Repository

@Repository
interface PointRepository {
    fun findPointByPointId(pointId: Long): Point?
    fun save(point: Point): Point
}