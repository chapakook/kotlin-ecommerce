package kr.hhplus.be.server.infrastructure.kafka

import kr.hhplus.be.server.TestcontainersConfiguration
import kr.hhplus.be.server.support.OutsideTopic
import org.apache.kafka.clients.admin.NewTopic
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.KafkaTemplate
import java.time.Duration

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class KafkaIntegrationTest @Autowired constructor(
    private val kafkaTemplate: KafkaTemplate<String, String>,
    private val consumerFactory: ConsumerFactory<String, ByteArray>
) {
    @Test
    @Order(1)
    fun `happy - 카프카 AdminClient로 토픽을 생성한다`() {
        val adminClient = TestcontainersConfiguration.getAdminClient()
        adminClient.use { client ->
            val newTopic = NewTopic(OutsideTopic.TEST_V1.topic, 3, 1)
            client.createTopics(listOf(newTopic)).all().get()
            val topics = client.listTopics().names().get()
            assertThat(topics).contains(OutsideTopic.TEST_V1.topic)
        }
    }

    @Test
    fun `happy - producer가 메시지를 전송하고 consumer가 수신한다`() {
        // given
        val key = "test-key"
        val value = "test-value"

        // when
        kafkaTemplate.send(OutsideTopic.TEST_V1.topic, key, value).get() // 동기 전송

        // then
        consumerFactory.createConsumer().use { consumer ->
            consumer.subscribe(listOf(OutsideTopic.TEST_V1.topic))
            val records = consumer.poll(Duration.ofSeconds(5))
            val record = records.firstOrNull()
            assertThat(record).isNotNull
            assertThat(record?.key()).isEqualTo(key)
            assertThat(String(record!!.value(), Charsets.UTF_8).removeSurrounding("\"")).isEqualTo(value)
        }
    }
}