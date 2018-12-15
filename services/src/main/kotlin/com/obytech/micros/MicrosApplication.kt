package com.obytech.micros

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class MicrosApplication

fun main(args: Array<String>) {
    runApplication<MicrosApplication>(*args)
}
