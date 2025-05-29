package kr.hhplus.be.server

import com.redis.testcontainers.RedisContainer
import jakarta.annotation.PreDestroy
import org.apache.kafka.clients.admin.AdminClient
import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.context.annotation.Configuration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.kafka.KafkaContainer
import org.testcontainers.utility.DockerImageName
import java.util.*

@Configuration
class TestcontainersConfiguration {
    @PreDestroy
    fun preDestroy() {
        if (mySqlContainer.isRunning) mySqlContainer.stop()
        if (redisContainer.isRunning) redisContainer.stop()
        if (kafkaContainer.isRunning) kafkaContainer.stop()
    }

    companion object {
        val mySqlContainer: MySQLContainer<*> = MySQLContainer(DockerImageName.parse("mysql:8.0"))
            .withDatabaseName("hhplus")
            .withUsername("test")
            .withPassword("test")
            .withInitScript("init.sql")
            .apply {
                start()
            }

        val redisContainer: RedisContainer = RedisContainer(DockerImageName.parse("redis:7.4.3"))
            .withExposedPorts(6379)
            .apply {
                start()
            }

        val kafkaContainer: KafkaContainer = KafkaContainer(
            DockerImageName.parse("apache/kafka-native:3.8.0"),
        ).apply {
            portBindings = listOf("9999:9999")
            start()
        }

        val bootstrapServers: String
            get() = kafkaContainer.bootstrapServers

        fun getAdminClient(): AdminClient {
            val props = Properties()
            props[AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG] = bootstrapServers
            return AdminClient.create(props)
        }

        init {
            System.setProperty(
                "spring.datasource.url",
                mySqlContainer.getJdbcUrl() + "?characterEncoding=UTF-8&serverTimezone=UTC"
            )
            System.setProperty("spring.datasource.username", mySqlContainer.username)
            System.setProperty("spring.datasource.password", mySqlContainer.password)
            System.setProperty("spring.data.redis.host", redisContainer.host)
            System.setProperty("spring.data.redis.port", "${redisContainer.getMappedPort(6379)}")
        }
    }
}
