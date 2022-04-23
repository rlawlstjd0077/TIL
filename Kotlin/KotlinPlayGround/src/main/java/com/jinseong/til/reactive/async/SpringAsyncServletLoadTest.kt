package com.jinseong.til.reactive.async

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.util.StopWatch
import org.springframework.web.client.RestTemplate
import java.util.concurrent.Callable
import java.util.concurrent.CyclicBarrier
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SpringAsyncServletLoadTest {
}

class Test {
    private val log: Logger = LoggerFactory.getLogger(Test::class.java)

    fun main() {
        val es = Executors.newFixedThreadPool(100)

        val rt = RestTemplate()
        val url = "http://localhost:8080/rest"

        val cyclicBarrier = CyclicBarrier(101)

        for (i in 0 .. 99) {
            println(i)

            es.submit {
                cyclicBarrier.await()

                log.info("Thread $i")
                val sw = StopWatch()
                sw.start()

                rt.getForObject("http://localhost:8080/rest?idx=$i", String::class.java)

                sw.stop()
                log.info("Elapsed: " + i + " -> " + sw.totalTimeSeconds)
                return@submit
            }
        }

        cyclicBarrier.await()

        val stopWatch = StopWatch()
        stopWatch.start()

        es.shutdown()
        es.awaitTermination(100, TimeUnit.SECONDS)

        stopWatch.stop()
        log.info("Total: " + stopWatch.totalTimeSeconds)

    }
}

fun main() {
    val test = Test()
    test.main()
}

