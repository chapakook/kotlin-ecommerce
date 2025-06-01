package kr.hhplus.be.server.infrastructure.couponevent

import kr.hhplus.be.server.domain.couponevent.CouponEventEvent
import kr.hhplus.be.server.domain.couponevent.OutsideCouponEventEventProducer
import kr.hhplus.be.server.support.OutsideTopic
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OutsideCouponEventKafkaEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, CouponEventEvent.CouponIssueRequest>,
) : OutsideCouponEventEventProducer {
    private val log = LoggerFactory.getLogger(this.javaClass)
    override fun send(event: CouponEventEvent.CouponIssueRequest) {
        log.info("📨 sending event: $event")
        kafkaTemplate.send(OutsideTopic.ISSUE_REQUEST_COUPON_V1.topic, event)
    }
}