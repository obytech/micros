package com.obytech.micros.controllers

import org.springframework.beans.factory.annotation.Value
import org.springframework.cloud.context.config.annotation.RefreshScope
import org.springframework.context.annotation.Profile
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger

@RestController
@Profile("pong")
@RefreshScope
class PongController (@Value("\${server.port}") var port: Int,
                      @Value("\${pong.msg:pong, pong, ponngggg. }") var msg: String) {



    private val pingCounter = AtomicInteger()

    @RequestMapping(value = ["/{name}"])
    fun hello(@PathVariable name: String = "world"): String = "hello ${name.formatName()}"

    @RequestMapping(value = ["/"])
    fun helloDefault(): String = hello()

    fun String.formatName(): String {
        val tokens = this.split(" ")
        var formatted = ""
        tokens.forEach { formatted += it[0].toUpperCase() + it.substring(1) + " " }

        return formatted.removeSuffix(" ")
    }

    @GetMapping("/ping")
    @RefreshScope
    fun ping() = "$msg(${pingCounter.incrementAndGet()}) [$port]".formatName()
}