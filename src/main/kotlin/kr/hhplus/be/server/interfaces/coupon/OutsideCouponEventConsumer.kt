package kr.hhplus.be.server.interfaces.coupon

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kr.hhplus.be.server.domain.coupon.CouponCommand
import kr.hhplus.be.server.domain.coupon.CouponService
import kr.hhplus.be.server.domain.couponevent.CouponEventCommand
import kr.hhplus.be.server.domain.couponevent.CouponEventEvent
import kr.hhplus.be.server.domain.couponevent.CouponEventService
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.handler.annotation.Header
import org.springframework.stereotype.Component

@Component
class OutsideCouponEventConsumer(
    private val couponService: CouponService,
    private val couponEventService: CouponEventService,
) {
    @KafkaListener(topics = ["outside.coupon.v1.issue.request"], groupId = "e-client-id-coupon-event")
    fun handler(
        message: ByteArray,
        @Header(KafkaHeaders.RECEIVED_KEY) key: String?,
        ack: Acknowledgment
    ) {
        val objectMapper = jacksonObjectMapper()
        val payload = objectMapper.readValue<CouponEventEvent.CouponIssueRequest>(message)
        if (couponEventService.hasIssued(CouponEventCommand.HasIssued(payload.couponEventId, payload.userId))) {
            ack.acknowledge()
            return
        }
        val couponEventInfo = couponEventService.issue(CouponEventCommand.Issue(payload.couponEventId))
        couponService.issue(
            CouponCommand.Issue(
                payload.userId,
                couponEventInfo.type,
                couponEventInfo.value,
                couponEventInfo.expiryMillis
            )
        )
        couponEventService.set(CouponEventCommand.SetCouponEvent(payload.couponEventId, payload.userId))
        ack.acknowledge()
    }
}