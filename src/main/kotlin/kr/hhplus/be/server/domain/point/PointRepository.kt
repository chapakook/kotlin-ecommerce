package kr.hhplus.be.server.domain.point

interface PointRepository {
    fun findPointById(id: Long): PointInfo
    fun updatePointById(id: Long, amount: Long): PointInfo
}