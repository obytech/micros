package com.obytech.fibo.web

import com.obytech.fibo.services.FiboService
import io.vertx.core.Vertx
import io.vertx.ext.web.Router
import java.security.SecureRandom
import java.time.LocalDateTime
import java.util.concurrent.atomic.AtomicInteger

class FiboController(private var motd: String) {

    private lateinit var firstIteration: AtomicInteger

    fun init() {
        firstIteration = AtomicInteger(SecureRandom().nextInt(10))
        FiboService.init(firstIteration.get())
        val vertx = Vertx.vertx()
        val router = Router.router(vertx)
        router.get("/")
                .handler {
                    val res = it.response()
                    res.putHeader("Fibo.timestamp", LocalDateTime.now().toString())
                    res.end(getResponse())
                }

        router.post("/reset").handler {
            FiboService.reset()
            it.response().end()
        }

        router.get("/*")
                .failureHandler {
                    it.response().statusCode = 404
                }

        vertx.createHttpServer()
                .requestHandler(router)
                .listen(9090)

        println("Init with motd: $motd")
    }

    private fun getResponse(): String {
        val (fibo, iter) = FiboService.getNextFiboWithIterations()
        return "$iter: $fibo\nFirst iteration was: $firstIteration(motd=$motd)"
    }
}