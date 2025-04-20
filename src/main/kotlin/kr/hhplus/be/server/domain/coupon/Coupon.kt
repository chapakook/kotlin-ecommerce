package kr.hhplus.be.server.domain.coupon

import kr.hhplus.be.server.support.ErrorCode.*
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset

enum class CouponType {
    FIXED,
    RATE
}

class Coupon(
    val couponId: Long,
    val userId: Long,
    val type: CouponType,
    val value: Long,
    val expiryMillis: Long,
    val createMillis: Long,
    var updateMillis: Long,
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
            return Coupon(0L, userId, type, value, expiryMillis, toDay, toDay, true)
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