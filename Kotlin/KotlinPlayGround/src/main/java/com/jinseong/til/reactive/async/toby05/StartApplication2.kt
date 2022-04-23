package com.jinseong.til.reactive.async.toby05

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.Future
import javax.annotation.PostConstruct

@SpringBootApplication
open class RemoteServiceApplication {

    @PostConstruct
    fun initialize() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC))
    }
}

@RestController
class RemoteController {
    @GetMapping("/service")
    fun rest(req: String): String {
        println("Request 받음")
        Thread.sleep(2000)
        return "$req/service"
    }

}

fun main(args: Array<String>) {
    System.setProperty("server.port", "8081")
    System.setProperty("server.tomcat.max-threads", "1000")
    System.setProperty("server.tomcat.threads.max", "1000")
    runApplication<RemoteServiceApplication>(*args)
}
