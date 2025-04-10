package kr.hhplus.be.server.domain.point

import org.springframework.stereotype.Repository

@Repository
interface PointRepository {
    fun findPointById(id: Long): PointInfo.Point
    fun findUserCoupon(code: String): PointInfo.UserCoupon?
    fun updatePointById(id: Long, amount: Long): PointInfo.Point
}