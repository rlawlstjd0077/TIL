package com.jinseong.til.reactive

import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZoneOffset
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
open class Application {

    @PostConstruct
    fun initialize() {
        TimeZone.setDefault(TimeZone.getTimeZone(ZoneOffset.UTC))
    }
}

@RestController
class Controller {
    @RequestMapping("/hello")
    fun hello(name: String): Publisher<String> {
        return Publisher {
            it.onSubscribe(object :  Subscription {
                override fun request(n: Long) {
                    it.onNext("Hello $name")
                    it.onComplete()
                }

                override fun cancel() {

                }
            })
        }
    }
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}
