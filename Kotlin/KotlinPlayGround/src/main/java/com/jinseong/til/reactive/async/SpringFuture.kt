package com.jinseong.til.reactive.async

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.AsyncResult
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.stereotype.Component
import org.springframework.util.concurrent.ListenableFuture
import java.util.concurrent.ExecutorService
import java.util.concurrent.Future
import javax.annotation.PostConstruct


@Component
open class MyService {
    private val log: Logger = LoggerFactory.getLogger(MyService::class.java)

    @Async
    open fun hello(): ListenableFuture<String> {
        log.info("hello()")
        Thread.sleep(1000)
        return AsyncResult("Hello")
    }
}

@SpringBootApplication
@EnableAsync
open class Application {

    private val log: Logger = LoggerFactory.getLogger(Application::class.java)

    @Autowired
    private lateinit var myService: MyService

    @PostConstruct
    fun initialize() {
        log.info("run()")
        val future = myService.hello()
        future.addCallback(
            { println(it) },
            { println(it) }
        )

        log.info("exit : " + future.isDone)
    }
}

fun main(args: Array<String>) {
    val runApplication = runApplication<Application>(*args)
}
