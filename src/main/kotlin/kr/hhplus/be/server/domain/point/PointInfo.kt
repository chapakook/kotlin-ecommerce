package kr.hhplus.be.server.domain.point
class PointInfo{
    data class Point(
        val pointId: Long,
        val userId: Long,
        val balance: Long,
    )
    data class UserCoupon(
        val discount: Long,
    )
}
