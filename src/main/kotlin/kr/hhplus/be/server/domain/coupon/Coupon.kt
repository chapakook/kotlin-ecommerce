package kr.hhplus.be.server.domain.coupon

import jakarta.persistence.*
import kr.hhplus.be.server.support.ErrorCode.*
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

enum class CouponType {
    FIXED,
    RATE
}

@Entity
@Table(name = "coupons")
class Coupon(
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
        fun issue(
            userId: Long,
            type: CouponType,
            value: Long,
            expiryMillis: Long,
        ): Coupon {
            require(isAfter(expiryMillis)) { EXPIRED_COUPON_EVENT.message }
            val toDay = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
            return Coupon(
                0L,
                userId,
                type,
                value,
                expiryMillis,
                toDay,
                toDay,
                true
            )
        }

        private fun isBefore(millis: Long): Boolean =
            !LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneOffset.UTC).toLocalDate()
                .isAfter(LocalDate.now(ZoneOffset.UTC))

        private fun isAfter(millis: Long): Boolean =
            !LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneOffset.UTC).toLocalDate()
                .isBefore(LocalDate.now(ZoneOffset.UTC))
    }

    fun use(amount: Long): Long {
        require(isActive) { USED_COUPON.message }
        require(isAfter(expiryMillis)) { EXPIRED_COUPON.message }
        isActive to true
        updateMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
        return when (type) {
            CouponType.FIXED -> if (value > amount) 0 else amount - value
            CouponType.RATE -> (amount - (amount * (value / 100.0))).toLong()
        }
    }
}