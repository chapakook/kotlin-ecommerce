package kr.hhplus.be.server.infrastructure.couponevent

import jakarta.persistence.*
import kr.hhplus.be.server.domain.coupon.CouponType
import kr.hhplus.be.server.domain.couponevent.CouponEvent

@Entity
@Table(name = "coupon_events")
class CouponEventEntity(
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
    companion object {
        fun of(event: CouponEvent): CouponEventEntity =
            with(event) { CouponEventEntity(couponEventId, maxCount, currentCount, type, value, expiryMillis) }
    }

    fun to(): CouponEvent = CouponEvent(couponEventId, maxCount, currentCount, type, value, expiryMillis)
}