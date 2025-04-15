package kr.hhplus.be.server.interfaces.point

import kr.hhplus.be.server.domain.point.PointInfo.PointInfo

class PointResponse {
    data class ChargeResult(val balance: Long) {
        companion object {
            fun from(info: PointInfo): ChargeResult = ChargeResult(info.balance)
        }
    }
}