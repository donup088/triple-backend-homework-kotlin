package com.triple.kotprac.common.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.StringRedisSerializer
import java.time.Duration


@Configuration
@Profile(value = ["local"])
class RedisConfig {
    @Value("\${spring.redis.host}")
    private val redisHost: String? = null

    @Value("\${spring.redis.port}")
    private val redisPort = 0

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory {
        val lettuceClientConfiguration = LettuceClientConfiguration.builder()
            .commandTimeout(Duration.ofMinutes(1))
            .shutdownTimeout(Duration.ZERO)
            .build()
        val redisStandaloneConfiguration = RedisStandaloneConfiguration(redisHost!!, redisPort)
        return LettuceConnectionFactory(redisStandaloneConfiguration, lettuceClientConfiguration)
    }

    @Bean
    fun redisTemplate(): RedisTemplate<*, *> {
        val redisTemplate = RedisTemplate<Any, Any>()
        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = GenericJackson2JsonRedisSerializer()
        redisTemplate.setConnectionFactory(redisConnectionFactory())
        redisTemplate.setEnableTransactionSupport(true)
        return redisTemplate
    }
}