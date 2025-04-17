package kr.hhplus.be.server.domain.couponevent

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import kr.hhplus.be.server.domain.coupon.CouponType
import kr.hhplus.be.server.support.ErrorCode.MAX_ISSUED_COUPON

@Entity
class CouponEvent(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val couponEventId: Long,
    val maxCount: Int,
    var currentCount: Int,
    val type: CouponType,
    val value: Long,
    val expiryMillis: Long,
) {
    fun issue() {
        require(maxCount > currentCount) { MAX_ISSUED_COUPON.message }
        currentCount++
    }
}