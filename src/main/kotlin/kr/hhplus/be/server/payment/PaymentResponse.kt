package kr.hhplus.be.server.payment

import java.time.LocalDateTime

enum class PaymentStatus {
    SUCCESS, FAILURE
}

data class PaymentResponse(
    val paymentId : Long,
    val orderId : Long,
    val amount : Int,
    val paymentStatus : PaymentStatus,
    val createdAt : LocalDateTime,
)
