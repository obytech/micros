package com.obytech.micros.controllers

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping

@Profile("ping")
@FeignClient("pong-service")//(name = "ping-service", url = "ping-service")
interface PongClient {

    @GetMapping("/ping")
    fun ping(): String

}