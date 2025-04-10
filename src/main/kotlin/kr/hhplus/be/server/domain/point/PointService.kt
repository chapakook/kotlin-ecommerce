package kr.hhplus.be.server.domain.point

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PointService(
    private val pointRepository: PointRepository,
) {
    fun getPoint(getCmd: PointCommand.Get): PointInfo.Point {
        return pointRepository.findPointById(getCmd.userId)
    }

    @Transactional
    fun chargePoint(chargeCmd: PointCommand.Charge): PointInfo.Point {
        return pointRepository.updatePointById(chargeCmd.userId, chargeCmd.amount)
    }

    @Transactional
    fun usePoint(useCmd: PointCommand.Use): PointInfo.Point {
        val point = pointRepository.findPointById(useCmd.userId)
        require(point.balance > 0) { "포인트가 부족합니다." }
        require(point.balance >= useCmd.amount) { "사용금액보다 포인트가 부족합니다."}
        return pointRepository.updatePointById(useCmd.userId, useCmd.amount)
    }
}