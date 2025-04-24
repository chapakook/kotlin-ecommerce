package kr.hhplus.be.server.domain.point

import kr.hhplus.be.server.domain.point.PointCommand.*
import kr.hhplus.be.server.domain.point.PointInfo.PointInfo
import kr.hhplus.be.server.support.ErrorCode.*
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointService(
    private val pointRepository: PointRepository,
) {
    fun find(findCmd: Find): PointInfo = pointRepository.findByUserId(findCmd.userId)?.let { point: Point ->
        PointInfo.of(point)
    } ?: throw NoSuchElementException(USER_NOT_FOUND.message)

    @Transactional
    fun charge(chargeCmd: Charge): PointInfo {
        var count = 0
        while (3 > count) {
            try {
                return pointRepository.findByUserId(chargeCmd.userId)?.let { point: Point ->
                    point.charge(chargeCmd.amount)
                    pointRepository.saveAndFlush(point)
                    PointInfo.of(point)
                } ?: throw NoSuchElementException(USER_NOT_FOUND.message)
            } catch (e: OptimisticLockingFailureException) {
                Thread.sleep(100L)
                count++
            }
        }
        throw OptimisticLockingFailureException(POINT_CHARGE_FAILED.message)
    }

    @Transactional
    fun use(useCmd: Use): PointInfo {
        var count = 0
        while (3 > count) {
            try {
                return pointRepository.findByUserId(useCmd.userId)?.let { point: Point ->
                    point.use(useCmd.amount)
                    pointRepository.saveAndFlush(point)
                    PointInfo.of(point)
                } ?: throw NoSuchElementException(USER_NOT_FOUND.message)
            } catch (e: OptimisticLockingFailureException) {
                Thread.sleep(100L)
                count++
            }
        }
        throw OptimisticLockingFailureException(POINT_USE_FAILED.message)
    }
}