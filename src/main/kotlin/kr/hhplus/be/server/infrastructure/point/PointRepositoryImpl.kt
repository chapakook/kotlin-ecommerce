package kr.hhplus.be.server.infrastructure.point

import kr.hhplus.be.server.domain.point.Point
import kr.hhplus.be.server.domain.point.PointRepository
import org.springframework.stereotype.Repository
import org.springframework.transaction.annotation.Transactional

@Repository
class PointRepositoryImpl(
    private val pointJPARepository: PointJPARepository,
) : PointRepository {
    override fun findByUserId(userId: Long): Point? = pointJPARepository.findByUserId(userId)?.to()

    @Transactional
    override fun save(point: Point): Point = pointJPARepository.save(PointEntity.of(point)).to()
}