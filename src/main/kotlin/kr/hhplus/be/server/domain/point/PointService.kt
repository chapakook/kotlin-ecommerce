package kr.hhplus.be.server.domain.point

import kr.hhplus.be.server.domain.point.PointCommand.*
import kr.hhplus.be.server.domain.point.PointInfo.PointInfo
import kr.hhplus.be.server.support.ErrorCode.*
import org.springframework.dao.OptimisticLockingFailureException
import org.springframework.retry.annotation.Backoff
import org.springframework.retry.annotation.Retryable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointService(
    private val pointRepository: PointRepository,
) {
    fun find(cmd: Find): PointInfo = pointRepository.findByUserId(cmd.userId)?.let { point: Point ->
        PointInfo.of(point)
    } ?: throw NoSuchElementException(USER_NOT_FOUND.message)

    @Transactional
    @Retryable(
        value = [OptimisticLockingFailureException::class],
        maxAttempts = 3,
        backoff = Backoff(delay = 500, multiplier = 1.5),
    )
    fun charge(cmd: Charge): PointInfo {
        try {
            return pointRepository.findByUserId(cmd.userId)?.let { point: Point ->
                point.charge(cmd.amount)
                pointRepository.save(point)
                PointInfo.of(point)
            } ?: throw NoSuchElementException(USER_NOT_FOUND.message)
        } catch (e: OptimisticLockingFailureException) {
            throw OptimisticLockingFailureException(POINT_CHARGE_FAILED.message)
        }
    }

    @Transactional
    @Retryable(
        value = [OptimisticLockingFailureException::class],
        maxAttempts = 3,
        backoff = Backoff(delay = 500, multiplier = 1.5),
    )
    fun use(cmd: Use): PointInfo {
        try {
            return pointRepository.findByUserId(cmd.userId)?.let { point: Point ->
                point.use(cmd.amount)
                pointRepository.save(point)
                PointInfo.of(point)
            } ?: throw NoSuchElementException(USER_NOT_FOUND.message)
        } catch (e: OptimisticLockingFailureException) {
            throw OptimisticLockingFailureException(POINT_USE_FAILED.message)
        }
    }
}