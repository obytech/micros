package com.obytech.micros.config

import com.netflix.discovery.EurekaClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("pong")
class PongConfig {

    @Autowired
    private lateinit var eurekaClient: EurekaClient

}