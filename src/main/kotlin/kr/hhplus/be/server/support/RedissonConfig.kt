package kr.hhplus.be.server.support

import org.redisson.Redisson
import org.redisson.api.RedissonClient
import org.redisson.config.Config
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RedissonConfig {
    @Bean
    fun redissonClient(
        @Value("\${spring.data.redis.host}") host: String,
        @Value("\${spring.data.redis.port}") port: Int
    ): RedissonClient =
        Redisson.create(Config().apply {
            useSingleServer().apply {
                address = "redis://$host:$port"
                timeout = 120000
                retryAttempts = 5
                retryInterval = 1500
            }
        })
}