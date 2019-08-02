package com.obytech.fibo

import com.obytech.fibo.web.FiboController
import io.vertx.config.ConfigRetriever
import io.vertx.config.ConfigRetrieverOptions
import io.vertx.config.ConfigStoreOptions
import io.vertx.core.Vertx
import io.vertx.core.VertxOptions
import io.vertx.core.json.JsonObject
import io.vertx.ext.web.client.WebClient
import io.vertx.ext.web.client.WebClientOptions
import io.vertx.spi.cluster.hazelcast.ConfigUtil
import io.vertx.spi.cluster.hazelcast.HazelcastClusterManager
import java.net.InetAddress

fun main() {
    Vertx.clusteredVertx(vertxOpts()) {
        initConfig(it.result())
    }
}

private fun initConfig(vertx: Vertx) {
    val config = ConfigStoreOptions().setType("file")
            .setConfig(JsonObject().put("path", "config.json"))

    val configRetrieverOpt = ConfigRetrieverOptions().addStore(config)
    val configRetriever = ConfigRetriever.create(vertx, configRetrieverOpt)
    configRetriever.getConfig {
        val obj = it.result()
        initRemoteConfig(vertx, obj)
    }
}

private fun initRemoteConfig(vertx: Vertx, json: JsonObject) {
    val serviceName = json.getString("service.name")
    val configObj = json.getJsonObject("config")
    val configHost = configObj.getString("host")
    val configPort = configObj.getInteger("port")
    val configBranch = configObj.getString("branch")

    val opts = WebClientOptions()
            .setUserAgent("pizdatz/blat")

    var motd: String? = null
    WebClient.create(vertx, opts)
            .get(configPort, configHost, "/$serviceName/$configBranch")
//            .putHeader("accept", "application/json;charset=UTF-8")
            .send {
                try {
                    if (!it.succeeded()) {
                        println("Failed to retrieve response: ${it.cause()}")
                        return@send
                    }

                    val remoteConfig = it.result().bodyAsJsonObject()
                    motd = remoteConfig.getJsonArray("propertySources")
                            .getJsonObject(0)
                            .getJsonObject("source")
                            .getString("motd")
                } finally {
                    FiboController(motd ?: json.getString("motd")).init()
                }
            }
}

private fun localAddress() = InetAddress.getLocalHost().hostAddress

fun vertxOpts(): VertxOptions {
    val local = localAddress()

    val hazelConf = ConfigUtil.loadConfig()
    hazelConf.groupConfig.name = "eb-cluster"
//        hazelConf.networkConfig.interfaces = InterfacesConfig().addInterface("172.23.0.1")
    val opts = VertxOptions()
            .setClusterManager(HazelcastClusterManager(hazelConf))
    opts.eventBusOptions.isClustered = true
//        opts.eventBusOptions.clusterPublicHost = "bus1"
    opts.eventBusOptions.host = local

    return opts
}