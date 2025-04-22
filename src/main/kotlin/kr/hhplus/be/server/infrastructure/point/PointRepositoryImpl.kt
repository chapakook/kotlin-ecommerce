package kr.hhplus.be.server.infrastructure.point

import kr.hhplus.be.server.domain.point.Point
import kr.hhplus.be.server.domain.point.PointRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PointRepositoryImpl(
    private val pointJPARepository: PointJPARepository,
) : PointRepository {
    override fun findPointByPointId(pointId: Long): Point? = pointJPARepository.findPointByPointId(pointId)

    @Transactional
    override fun save(point: Point): Point = pointJPARepository.save(point)
}