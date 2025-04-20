package kr.hhplus.be.server.domain.point

import kr.hhplus.be.server.domain.point.PointCommand.*
import kr.hhplus.be.server.domain.point.PointInfo.PointInfo
import kr.hhplus.be.server.support.ErrorCode.USER_NOT_FOUND
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointService(
    private val pointRepository: PointRepository,
) {
    fun find(findCmd: Find): PointInfo = pointRepository.findPointById(findCmd.userId)?.let { point: Point ->
        PointInfo.of(point)
    } ?: throw NoSuchElementException(USER_NOT_FOUND.message)

    @Transactional
    fun charge(chargeCmd: Charge): PointInfo =
        pointRepository.findPointById(chargeCmd.userId)?.let { point: Point ->
            point.charge(chargeCmd.amount)
            pointRepository.save(point)
            PointInfo.of(point)
        } ?: throw NoSuchElementException(USER_NOT_FOUND.message)

    @Transactional
    fun use(useCmd: Use): PointInfo = pointRepository.findPointById(useCmd.userId)?.let { point: Point ->
        point.use(useCmd.amount)
        pointRepository.save(point)
        PointInfo.of(point)
    } ?: throw NoSuchElementException(USER_NOT_FOUND.message)
}