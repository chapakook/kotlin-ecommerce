package kr.hhplus.be.server.domain.point

class PointInfo {
    class PointInfo(
        val pointId: Long,
        val userId: Long,
        val balance: Long,
        val updateMillis: Long,
        var version: Long,
    ) {
        companion object {
            fun of(point: Point): PointInfo = with(point) { PointInfo(pointId, userId, balance, updateMillis, version) }
        }
    }
}