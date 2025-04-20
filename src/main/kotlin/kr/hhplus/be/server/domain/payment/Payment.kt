package kr.hhplus.be.server.domain.payment

import java.time.LocalDateTime
import java.time.ZoneOffset


class Payment(
    val paymentId: Long = 0L,
    val orderId: Long,
    val amount: Long,
    val createMillis: Long = LocalDateTime.now().toInstant(ZoneOffset.UTC).toEpochMilli(),
)