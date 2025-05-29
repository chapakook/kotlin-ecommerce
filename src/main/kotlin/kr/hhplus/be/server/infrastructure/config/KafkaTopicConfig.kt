package kr.hhplus.be.server.infrastructure.config

import kr.hhplus.be.server.support.OutsideTopic
import org.apache.kafka.clients.admin.NewTopic
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class KafkaTopicConfig {
    @Bean
    fun orderCompletedTopic(): NewTopic = NewTopic(OutsideTopic.COMPLETED_V1.topic, 3, 1)
}