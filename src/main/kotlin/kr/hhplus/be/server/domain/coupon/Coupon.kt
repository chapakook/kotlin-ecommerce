package kr.hhplus.be.server.domain.coupon

import kr.hhplus.be.server.support.ErrorCode.EXPIRED_COUPON
import kr.hhplus.be.server.support.ErrorCode.USED_COUPON
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
        ): Coupon = Coupon(
            0L,
            userId,
            type,
            value,
            expiryMillis,
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
            true
        )
    }

    fun use(amount: Long): Long {
        require(isActive) { USED_COUPON.message }
        require(validExpiry()) { EXPIRED_COUPON.message }
        isActive to true
        updateMillis = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli()
        return when (type) {
            CouponType.FIXED -> if (value > amount) 0 else amount - value
            CouponType.RATE -> amount * (1 - value / 100)
        }
    }

    private fun validExpiry(): Boolean =
        LocalDateTime.ofInstant(Instant.ofEpochMilli(expiryMillis), ZoneOffset.UTC).toLocalDate()
            .isBefore(LocalDate.now(ZoneOffset.UTC))
}