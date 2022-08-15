package com.example.ff4jredissample.config

import io.lettuce.core.RedisClient
import io.lettuce.core.RedisURI
import org.ff4j.FF4j
import org.ff4j.cache.FF4jCacheManagerRedisLettuce
import org.ff4j.cache.FF4jCacheProxy
import org.ff4j.core.FeatureStore
import org.ff4j.property.store.PropertyStore
import org.ff4j.store.FeatureStoreRedisLettuce
import org.ff4j.store.PropertyStoreRedisLettuce
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary

@Configuration
class FF4jCacheConfig(
    @Value("\${redis.host}") private val redisHost: String,
    @Value("\${redis.port}") private val redisPort: Int,
) {

    @Bean
    fun getFF4jCacheable(): FF4j {
        val ff4j = FF4j()

        val redisURI = RedisURI.create(redisHost, redisPort)
        val redisCacheClient = RedisClient.create(redisURI)

        val ff4jCache = FF4jCacheManagerRedisLettuce(redisCacheClient)

        val fStore: FeatureStore = FeatureStoreRedisLettuce(redisCacheClient)
        val pStore: PropertyStore = PropertyStoreRedisLettuce(redisCacheClient)

        val ff4jCacheProxy = FF4jCacheProxy(fStore, pStore, ff4jCache)

        ff4j.featureStore = ff4jCacheProxy
        ff4j.propertiesStore = ff4jCacheProxy

        ff4j.audit(false)
        ff4j.autoCreate(true)

        return ff4j
    }
}