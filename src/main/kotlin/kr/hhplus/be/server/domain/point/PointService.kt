package kr.hhplus.be.server.domain.point

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointService(
) {
    fun getPoint(getCmd: PointCommand.Get): PointInfo {
        return PointInfo(1L,1L,100L)
    }

    @Transactional
    fun chargePoint(chargeCmd: PointCommand.Charge): PointInfo {
        return PointInfo(1L,1L,100L)
    }

    @Transactional
    fun usePoint(useCmd: PointCommand.Use): PointInfo {
        return PointInfo(1L,1L,100L)
    }
}