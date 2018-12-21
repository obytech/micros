package com.obytech.micros.config

import org.springframework.cloud.netflix.ribbon.RibbonClient
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.EnableScheduling

@Configuration
@Profile("ping")
@RibbonClient("pong-service")
@EnableScheduling
class PingConfig