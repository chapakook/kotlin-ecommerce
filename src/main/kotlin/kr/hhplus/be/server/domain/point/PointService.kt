package kr.hhplus.be.server.domain.point

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class PointService(
) {
    fun getPoint(getCmd: PointCommand.Get): Point {
        return Point(1L,1L,100L, LocalDateTime.now())
    }

    @Transactional
    fun chargePoint(chargeCmd: PointCommand.Charge): Point {
        return Point(1L,1L,100L, LocalDateTime.now())
    }

    @Transactional
    fun usePoint(useCmd: PointCommand.Use): Point {
        return Point(1L,1L,100L, LocalDateTime.now())
    }
}