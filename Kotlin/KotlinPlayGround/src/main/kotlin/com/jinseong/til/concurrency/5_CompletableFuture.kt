package com.jinseong.til.concurrency

import com.jinseong.til.concurrency.ConcurrencySupport.PERSISTENCE_FORK_FACTOR
import com.jinseong.til.concurrency.ConcurrencySupport.USERS
import com.jinseong.til.concurrency.ConcurrencySupport.persistence
import com.jinseong.til.concurrency.ConcurrencySupport.serviceA
import com.jinseong.til.concurrency.ConcurrencySupport.serviceB
import com.jinseong.til.concurrency.ConcurrencySupport.start
import com.jinseong.til.concurrency.ConcurrencySupport.stop
import java.util.concurrent.CompletableFuture
import java.util.concurrent.ForkJoinPool
import java.util.function.Function
import java.util.stream.IntStream
import kotlin.streams.asSequence
import kotlin.streams.toList


private val commonPool = ForkJoinPool(2000)


fun main() {
    shouldExecuteIterationsConcurrentlyCompletableFuture()
}

fun shouldExecuteIterationsConcurrentlyCompletableFuture() {
    start()

    CompletableFuture.allOf(*IntStream.rangeClosed(1, USERS)
        .boxed()
        .map { user: Int -> userFlow(user) }
        .toList()
        .toTypedArray()
    ).get()

    stop()
}

private fun userFlow(user: Int): CompletableFuture<String> {
    return CompletableFuture.supplyAsync<String>({ serviceA(user) }, commonPool)
        .thenCombine(CompletableFuture.supplyAsync({ serviceB(user)}, commonPool), ::persist)
}

private fun persist(serviceA: String, serviceB: String): String? {
    CompletableFuture.allOf(*IntStream.rangeClosed(1, PERSISTENCE_FORK_FACTOR)
        .boxed()
        .map { iteration: Int? ->
            CompletableFuture.runAsync(
                { persistence(iteration!!, serviceA, serviceB) }, commonPool
            )
        }
        .asSequence()
        .toList()
        .toTypedArray()
    ).join()

    return ""
}
