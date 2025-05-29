package kr.hhplus.be.server.infrastructure.order

import kr.hhplus.be.server.domain.order.OrderEvent
import kr.hhplus.be.server.domain.order.OutsideOrderEventProducer
import kr.hhplus.be.server.support.OutsideTopic
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class OutsideOrderKafkaEventProducer(
    private val kafkaTemplate: KafkaTemplate<String, OrderEvent.OrderCompleted>,
) : OutsideOrderEventProducer {
    override fun send(event: OrderEvent.OrderCompleted) {
        kafkaTemplate.send(OutsideTopic.COMPLETED_V1.topic, event)
    }
}