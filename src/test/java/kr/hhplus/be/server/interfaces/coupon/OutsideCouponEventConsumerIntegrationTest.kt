package kr.hhplus.be.server.interfaces.coupon

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import kr.hhplus.be.server.domain.couponevent.CouponEventEvent
import kr.hhplus.be.server.domain.couponevent.OutsideCouponEventEventProducer
import kr.hhplus.be.server.support.OutsideTopic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.annotation.Order
import org.springframework.kafka.core.ConsumerFactory
import java.time.Duration

@SpringBootTest
class OutsideCouponEventConsumerIntegrationTest @Autowired constructor(
    private val producer: OutsideCouponEventEventProducer,
    private val consumerFactory: ConsumerFactory<String, ByteArray>
) {
    private val log = LoggerFactory.getLogger(this.javaClass)

    @Order(1)
    @Test
    fun `happy - producer가 Kafka에 메시지를 보내면 Consumer가 처리한다`() {
        // given
        val events = List(5) { idx ->
            CouponEventEvent.CouponIssueRequest(
                couponEventId = 1L,
                userId = idx.toLong(),
            )
        }

        // when
        events.forEach { producer.send(it) }

        // then
        consumerFactory.createConsumer().use { consumer ->
            consumer.subscribe(listOf(OutsideTopic.ISSUE_REQUEST_COUPON_V1.topic))
            val records = consumer.poll(Duration.ofSeconds(10))
            assertThat(records.count()).isEqualTo(events.size)

            records.forEachIndexed { idx, record ->
                val objectMapper = jacksonObjectMapper()
                val payload = objectMapper.readValue<CouponEventEvent.CouponIssueRequest>(record.value())
                log.info("📩 consume message: $payload")
                assertThat(payload.couponEventId).isEqualTo(events[idx].couponEventId)
                assertThat(payload.userId).isEqualTo(events[idx].userId)
            }
        }
    }
}