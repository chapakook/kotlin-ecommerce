package kr.hhplus.be.server.domain.couponevent

import jakarta.persistence.*
import kr.hhplus.be.server.domain.coupon.CouponType
import kr.hhplus.be.server.support.ErrorCode.MAX_ISSUED_COUPON

@Entity
@Table(name = "coupon_events")
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