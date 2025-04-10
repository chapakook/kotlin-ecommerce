package kr.hhplus.be.server.domain.point

import org.springframework.stereotype.Service

@Service
class PointService(
    private val pointRepository: PointRepository,
) {
    fun find(getCmd: PointCommand.Get): PointInfo.Point {
        return pointRepository.findPointById(getCmd.userId)
    }

    fun charge(chargeCmd: PointCommand.Charge): PointInfo.Point {
        return pointRepository.updatePointById(chargeCmd.userId, chargeCmd.amount)
    }

    fun use(useCmd: PointCommand.Use): PointInfo.Point {
        var discount = 0L
        val userCoupon = pointRepository.findUserCoupon(useCmd.code?.isBlank().toString())
        if (userCoupon != null) { discount += userCoupon.discount }
        val point = pointRepository.findPointById(useCmd.userId)
        val amount =if (useCmd.amount - discount >= 0) useCmd.amount - discount else 0
        require(point.balance >= amount) { "사용금액보다 포인트가 부족합니다."}
        return pointRepository.updatePointById(useCmd.userId, amount)
    }
}