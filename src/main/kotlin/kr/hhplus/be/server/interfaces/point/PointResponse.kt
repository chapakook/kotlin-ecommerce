package kr.hhplus.be.server.interfaces.point

import kr.hhplus.be.server.domain.point.PointInfo.Point

class PointResponse {
    data class ChargeResult(val balance: Long) {
        companion object {
            fun from(point: Point): ChargeResult = ChargeResult(point.balance)
        }
    }
}