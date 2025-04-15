package kr.hhplus.be.server.interfaces.point

import kr.hhplus.be.server.domain.point.PointInfo.Info

class PointResponse {
    data class ChargeResult(val balance: Long) {
        companion object {
            fun from(info: Info): ChargeResult = ChargeResult(info.balance)
        }
    }
}