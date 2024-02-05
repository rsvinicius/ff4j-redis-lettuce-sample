package com.example.ff4jredissample.config

import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import org.ff4j.FF4j
import org.ff4j.store.FeatureStoreRedisLettuce
import org.ff4j.store.PropertyStoreRedisLettuce
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary


@Configuration
@Primary
class FF4jConfig(
        @Value("\${redis.host}") private val redisHost: String,
        @Value("\${redis.port}") private val redisPort: Int
) {

    @Bean
    @Primary
    fun getFF4j(): FF4j {
        val ff4j = FF4j()

        val redisURI = RedisURI.create(redisHost, redisPort)
        val redisClient = RedisClient.create(redisURI)

        ff4j.featureStore = FeatureStoreRedisLettuce(redisClient)
        ff4j.propertiesStore = PropertyStoreRedisLettuce(redisClient)
        ff4j.audit(false)

        return ff4j
    }
}