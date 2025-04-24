package kr.hhplus.be.server.interfaces.point

import kr.hhplus.be.server.domain.point.PointInfo.PointInfo

class PointResponse {
    data class PointV1(val balance: Long) {
        companion object {
            fun of(info: PointInfo): PointV1 = PointV1(info.balance)
        }
    }

    data class ChargeV1(val balance: Long) {
        companion object {
            fun of(info: PointInfo): ChargeV1 = ChargeV1(info.balance)
        }
    }
}
