package com.obytech.micros.services

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Profile
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.core.scheduler.Schedulers

@Profile("pong")
@Service
class PongService (@Autowired private val webClient: WebClient) {

    fun fetchFibo(): Mono<String> = Mono.just(webClient.get().uri("localhost:9090/"))
            .subscribeOn(Schedulers.elastic())
            .flatMap {
                it.retrieve().onStatus(
                        { status -> !status.is2xxSuccessful },
                        { res -> Mono.error(HttpServerErrorException(res.statusCode())) })
                        .bodyToMono(String::class.java)
            }
//            .switchIfEmpty (Mono.just (Mono.just("MpT")))
            .onErrorResume { Mono.just("Failed to retrieve fibo from fibo-service") }
}