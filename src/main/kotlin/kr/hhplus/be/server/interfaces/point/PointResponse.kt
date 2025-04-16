package kr.hhplus.be.server.interfaces.point

import kr.hhplus.be.server.domain.point.PointInfo.PointInfo

class PointResponse {
    data class ChargeV1(val balance: Long) {
        companion object {
            fun from(info: PointInfo): ChargeV1 = ChargeV1(info.balance)
        }
    }
}
