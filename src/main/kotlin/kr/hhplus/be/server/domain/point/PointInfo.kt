package kr.hhplus.be.server.domain.point

import java.time.LocalDateTime

data class Point(
    val pointId: Long,
    val userId: Long,
    val balance: Long,
    val updatedAt: LocalDateTime,
) {
    init {
        require(pointId > 0) { "pointId must be positive" }
        require(userId > 0) { "userId must be positive" }
        require(balance > 0) { "balance must be positive" }
    }
}