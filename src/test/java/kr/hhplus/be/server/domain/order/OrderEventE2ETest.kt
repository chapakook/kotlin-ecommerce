package kr.hhplus.be.server.domain.order

import kr.hhplus.be.server.domain.stock.StockEvent
import kr.hhplus.be.server.support.OutsideTopic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationEventPublisher
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.Duration

@SpringBootTest
class OrderEventE2ETest @Autowired constructor(
    private val testEventService: TestEventService,
    private val consumerFactory: ConsumerFactory<String, ByteArray>
) {
    @Test
    fun `happy - applicationEvent 발행부터 kafka topic 수신까지 end-to-end`() {
        // given
        val orderId = 1L
        val userId = 2L
        val couponId = 3L
        val productId = 4L
        val quantity = 3
        val totalAmount = 1000L
        // when
        testEventService.success(
            StockEvent.StockDeducted(
                orderId,
                userId,
                couponId,
                productId,
                quantity,
                totalAmount
            )
        )
        // then
        consumerFactory.createConsumer().use { consumer ->
            consumer.subscribe(listOf(OutsideTopic.COMPLETED_V1.topic))
            val records = consumer.poll(Duration.ofSeconds(10))
            val record = records.firstOrNull()
            assertThat(record).isNotNull
            consumer.commitSync()
        }
    }
}

@Service
class TestEventService(
    private val eventPublisher: ApplicationEventPublisher,
) {
    @Transactional
    fun success(event: StockEvent.StockDeducted) {
        eventPublisher.publishEvent(event)
    }
}