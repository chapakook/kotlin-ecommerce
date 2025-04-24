package kr.hhplus.be.server.infrastructure.point

import kr.hhplus.be.server.domain.point.Point
import kr.hhplus.be.server.domain.point.PointRepository
import org.springframework.stereotype.Repository

@Repository
class PointRepositoryImpl(
    private val pointJPARepository: PointJPARepository,
) : PointRepository {
    override fun findByUserId(userId: Long): Point? = pointJPARepository.findByUserId(userId)
    override fun save(point: Point): Point = pointJPARepository.save(point)
    override fun saveAndFlush(point: Point): Point = pointJPARepository.saveAndFlush(point)
}