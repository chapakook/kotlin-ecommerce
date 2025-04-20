package kr.hhplus.be.server.domain.point

import kr.hhplus.be.server.infrastructure.point.PointEntity
import kr.hhplus.be.server.support.ErrorCode.*
import java.time.LocalDateTime
import java.time.ZoneOffset

class Point(
    val pointId: Long,
    val userId: Long,
    var balance: Long,
    var updateMillis: Long,
) {
    companion object {
        fun of(entity: PointEntity): Point = with(entity) { Point(pointId, userId, balance, updateMillis) }

        fun create(pointId: Long, userId: Long, balance: Long): Point {
            require(balance > 0) { CREATE_BASE_POINT.message }
            return Point(pointId, userId, balance, LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli())
        }
    }

    fun charge(amount: Long) {
        require(amount > 0) { AMOUNT_MUST_BE_POSITIVE.message }
        balance += amount
        updateMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    }

    fun use(amount: Long) {
        require(balance >= amount) { OUT_OF_POINT.message }
        balance -= amount
        updateMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
    }
}