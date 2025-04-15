package kr.hhplus.be.server.domain.point

import kr.hhplus.be.server.domain.point.PointCommand.*
import kr.hhplus.be.server.domain.point.PointInfo.Info
import kr.hhplus.be.server.support.ErrorCode.*
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset

@Service
class PointService(
    private val pointRepository: PointRepository,
) {
    fun find(findCmd: Find): Info {
        val point = pointRepository.findPointById(findCmd.userId)
        return point?.let { Info.from(point) } ?: throw NoSuchElementException(USER_NOT_FOUND.message)
    }

    fun charge(chargeCmd: Charge): Info {
        require(chargeCmd.amount > 0) { AMOUNT_MUST_BE_POSITIVE.message }
        val point = pointRepository.findPointById(chargeCmd.userId)
        return point?.let {
            Info.from(
                pointRepository.update(
                    chargeCmd.userId,
                    point.balance + chargeCmd.amount,
                    LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
                )
            )
        } ?: throw NoSuchElementException(USER_NOT_FOUND.message)
    }

    fun use(useCmd: Use): Info {
        val point = pointRepository.findPointById(useCmd.userId)
        return point?.let {
            require(point.balance >= useCmd.amount) { OUT_OF_POINT.message }
            Info.from(
                pointRepository.update(
                    useCmd.userId,
                    point.balance - useCmd.amount,
                    LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
                )
            )
        } ?: throw NoSuchElementException(USER_NOT_FOUND.message)
    }
}