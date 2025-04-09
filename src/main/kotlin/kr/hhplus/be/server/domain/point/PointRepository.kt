package kr.hhplus.be.server.domain.point

interface PointRepository {
    fun findPointById(id: Long): Point
    fun updatePointById(id: Long, amount: Long): Point
}