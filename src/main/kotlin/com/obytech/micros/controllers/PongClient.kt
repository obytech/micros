package com.obytech.micros.controllers

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "pong-client", url = "localhost:8081")
interface PongClient {

    @GetMapping("/ping")
    fun ping(): String

}