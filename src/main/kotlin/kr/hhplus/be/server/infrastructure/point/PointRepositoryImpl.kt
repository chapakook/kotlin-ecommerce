package kr.hhplus.be.server.infrastructure.point

import kr.hhplus.be.server.domain.point.Point
import kr.hhplus.be.server.domain.point.PointRepository
import org.springframework.stereotype.Repository

@Repository
class PointRepositoryImpl(
    private val jpaPointRepository: JpaPointRepository,
) : PointRepository {
    override fun findByUserId(userId: Long): Point? = jpaPointRepository.findByUserId(userId)
    override fun save(point: Point): Point = jpaPointRepository.save(point)
}