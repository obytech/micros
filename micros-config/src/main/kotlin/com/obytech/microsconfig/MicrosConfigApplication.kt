package com.obytech.microsconfig

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.config.server.EnableConfigServer

@SpringBootApplication
@EnableConfigServer
class MicrosConfigApplication

fun main(args: Array<String>) {
	runApplication<MicrosConfigApplication>(*args)
}

