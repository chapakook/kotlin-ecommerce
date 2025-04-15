package kr.hhplus.be.server.domain.point

data class Point(
    val pointId: Long,
    val userId: Long,
    val balance: Long,
    val updateMillis: Long,
)