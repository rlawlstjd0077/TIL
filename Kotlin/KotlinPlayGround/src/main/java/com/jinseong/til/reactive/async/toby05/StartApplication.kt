package com.jinseong.til.reactive.async.toby05

import io.netty.channel.nio.NioEventLoopGroup
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.http.ResponseEntity
import org.springframework.http.client.Netty4ClientHttpRequestFactory
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.AsyncRestTemplate
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.time.ZoneOffset
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
@EnableAsync
open class StartApplication {

    @PostConstruct
    fun initialize() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC))
    }
}

@RestController
class Controller {
    val restTempalte = AsyncRestTemplate()

    @GetMapping("/rest")
    fun rest(idx: Int): ListenableFuture<ResponseEntity<String>> {
        val res = restTempalte
            .getForEntity<String>("http://localhost:8081/service?req=hello$idx", String::class.java)
        println("Marking .. $idx")
        return res
    }
}

fun main(args: Array<String>) {
    System.setProperty("server.tomcat.max-threads", "1")
    runApplication<StartApplication>(*args)
}


