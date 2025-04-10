package kr.hhplus.be.server.domain.point

data class PointInfo(
    val pointId: Long,
    val userId: Long,
    val balance: Long,
) {
    init {
        require(pointId > 0) { "pointId must be positive" }
        require(userId > 0) { "userId must be positive" }
        require(balance > 0) { "balance must be positive" }
    }
}