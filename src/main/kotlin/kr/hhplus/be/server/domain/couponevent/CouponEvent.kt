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
    @Column(nullable = false)
    val maxCount: Int,
    @Column(nullable = false)
    var currentCount: Int,
    @Column(nullable = false)
    val type: CouponType,
    @Column(nullable = false)
    val value: Long,
    @Column(nullable = false)
    val expiryMillis: Long,
) {
    fun issue() {
        require(maxCount > currentCount) { MAX_ISSUED_COUPON.message }
        currentCount++
    }
}