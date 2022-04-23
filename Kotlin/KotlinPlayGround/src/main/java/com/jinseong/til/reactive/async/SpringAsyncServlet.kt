package com.jinseong.til.reactive.async

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.context.request.async.DeferredResult
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitter
import java.util.concurrent.Callable
import java.util.concurrent.ConcurrentLinkedQueue
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import javax.annotation.PostConstruct
import kotlin.math.log


@SpringBootApplication
@EnableAsync
open class ServletApplication {

    @RestController
    class Controller {
        private val log: Logger = LoggerFactory.getLogger(Controller::class.java)

        private val queue = ConcurrentLinkedQueue<DeferredResult<String>>();

        @GetMapping("emitter")
        fun stream(): ResponseBodyEmitter {
            val emitter = ResponseBodyEmitter()

            Executors.newSingleThreadExecutor().submit {
                for (i in 0 .. 50) {
                    emitter.send("<p> Stream $i </p>")
                    Thread.sleep(100)
                }
                emitter.complete()
            }

            return emitter
        }

        @GetMapping("/dr")
        fun async(): DeferredResult<String> {
            log.info("callable")
            val deferredResult = DeferredResult<String>(60000L)

            queue += deferredResult
            return deferredResult
        }

        @GetMapping("/dr/count")
        fun drCount(): String {
            return queue.size.toString()
        }

        @GetMapping("/dr/event")
        fun drEvent(msg: String ): String {
            for (deferredResult in queue) {
                deferredResult.setResult("Hello $msg")
                queue.remove(deferredResult)
            }

            return "OK"
        }
//        fun async(): String {
//            log.info("async")
//            Thread.sleep(2000)
//            return "hello"
//        }
    }
}

fun main(args: Array<String>) {
    runApplication<ServletApplication>(*args)
}
