package kr.hhplus.be.server.domain.point

class PointInfo {
    class Info(
        val pointId: Long,
        val userId: Long,
        val balance: Long,
        val updateMillis: Long,
    ) {
        companion object {
            fun from(point: Point): Info = with(point) { Info(pointId, userId, balance, updateMillis) }
        }
    }
}