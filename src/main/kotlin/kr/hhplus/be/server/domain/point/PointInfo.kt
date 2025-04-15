package kr.hhplus.be.server.domain.point

import java.time.LocalDateTime
import java.time.ZoneOffset

class PointInfo {
    class PointInfo(
        val pointId: Long,
        val userId: Long,
        val balance: Long,
        val updateMillis: Long,
    ) {
        companion object {
            fun create(pointId: Long, userId: Long, balance: Long): PointInfo =
                PointInfo(pointId, userId, balance, LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())

            fun of(point: Point): PointInfo =
                PointInfo(point.pointId, point.userId, point.balance, point.updateMillis)
        }
    }
}