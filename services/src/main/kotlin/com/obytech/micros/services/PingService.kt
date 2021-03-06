package com.obytech.micros.services

import com.obytech.micros.controllers.PongClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
@Profile("ping")
class PingService (@Autowired val pongClient: PongClient) {

    @Scheduled(fixedDelay = 1000)
    fun pinger() = println(pongClient.ping())

}