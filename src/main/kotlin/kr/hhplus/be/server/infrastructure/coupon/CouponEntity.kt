package kr.hhplus.be.server.infrastructure.coupon

import jakarta.persistence.*
import kr.hhplus.be.server.domain.coupon.Coupon
import kr.hhplus.be.server.domain.coupon.CouponType

@Entity
@Table(name = "coupons")
class CouponEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val couponId: Long,
    @Column(nullable = false)
    val userId: Long,
    @Column(nullable = false)
    val type: CouponType,
    @Column(nullable = false)
    val value: Long,
    @Column(nullable = false)
    val expiryMillis: Long,
    @Column(nullable = false)
    val createMillis: Long,
    @Column(nullable = false)
    var updateMillis: Long,
    @Column(nullable = false)
    var isActive: Boolean = true,
) {
    companion object {
        fun of(coupon: Coupon): CouponEntity = with(coupon) {
            CouponEntity(
                couponId,
                userId,
                type,
                value,
                expiryMillis,
                createMillis,
                updateMillis,
                isActive
            )
        }
    }

    fun to(): Coupon = Coupon(couponId, userId, type, value, expiryMillis, createMillis, updateMillis, isActive)
}