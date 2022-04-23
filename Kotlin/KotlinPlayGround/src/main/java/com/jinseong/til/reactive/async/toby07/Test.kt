package com.jinseong.til.reactive.async.toby07

import org.slf4j.LoggerFactory
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.web.filter.DelegatingFilterProxy
import java.lang.RuntimeException
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.TimeUnit


/**
 * @author Jay
 */
fun main() {
    val es = Executors.newFixedThreadPool(10)

    CompletableFuture
        .supplyAsync({
            Thread.sleep(100000)
            log.info("runAsynbc")
            1
        }, es)
        .thenCompose {
            log.info("thenRun")
            CompletableFuture.completedFuture(it + 1)
        }
        .thenApplyAsync( {
            log.info("then Apply Another Thread")
            it * 3
        }, es)
        .exceptionally {
            -10
        }
        .thenAcceptAsync ({
            log.info("thenRun$it")
        }, es)

    log.info("exit")
    ForkJoinPool.commonPool().shutdown()
    ForkJoinPool.commonPool().awaitTermination(10, TimeUnit.SECONDS)
}

val log = LoggerFactory.getLogger("asfas")
