package com.obytech.microseureka

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@SpringBootApplication
@EnableEurekaServer
class MicrosEurekaApplication

fun main(args: Array<String>) {
	runApplication<MicrosEurekaApplication>(*args)
}

