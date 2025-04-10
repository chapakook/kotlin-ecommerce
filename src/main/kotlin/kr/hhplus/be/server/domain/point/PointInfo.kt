package kr.hhplus.be.server.domain.point
class PointInfo{
    data class Point(
        val pointId: Long,
        val userId: Long,
        val balance: Long,
    )
    data class UserCoupon(
        val userId:Long,
        val couponId: Long,
        val code: String,
        val type: String,
        val value: Long,
        val isActive: Boolean,
    )
}
