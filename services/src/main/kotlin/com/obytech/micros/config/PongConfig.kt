package com.obytech.micros.config

import com.netflix.discovery.EurekaClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.web.reactive.function.client.WebClient

@Configuration
@Profile("pong")
class PongConfig {

    @Autowired
    private lateinit var eurekaClient: EurekaClient

    @Bean
    fun webClient() = WebClient.create()

}