package com.jinseong.til.concurrency

import com.jinseong.til.concurrency.ConcurrencySupport.PERSISTENCE_FORK_FACTOR
import com.jinseong.til.concurrency.ConcurrencySupport.SERVICE_A_LATENCY
import com.jinseong.til.concurrency.ConcurrencySupport.SERVICE_B_LATENCY
import com.jinseong.til.concurrency.ConcurrencySupport.USERS
import com.jinseong.til.concurrency.ConcurrencySupport.fullConcurrencyExpectedTime
import com.jinseong.til.concurrency.ConcurrencySupport.persistence
import com.jinseong.til.concurrency.ConcurrencySupport.service
import com.jinseong.til.concurrency.ConcurrencySupport.start
import com.jinseong.til.concurrency.ConcurrencySupport.stop
import java.util.concurrent.*

private const val EXECUTOR_THREAD_COUNT = 20
private val executor: ExecutorService = Executors.newScheduledThreadPool(EXECUTOR_THREAD_COUNT)
private val latch = CountDownLatch(USERS)

fun main() {
    shouldExecuteIterationsConcurrently()
}

fun shouldExecuteIterationsConcurrently() {
    start()

    for (user in 1..USERS) {
        executor.execute(UserFlow2(user))
    }

    // Stop Condition
    latch.await()
    executor.shutdown()
    executor.awaitTermination(60, TimeUnit.SECONDS)

    stop(EXECUTOR_THREAD_COUNT, fullConcurrencyExpectedTime())
}

internal class UserFlow2(private val user: Int) : Runnable {
    override fun run() {
        val serviceA: Future<String> = executor.submit(Service2("A", SERVICE_A_LATENCY, user))
        val serviceB: Future<String> = executor.submit(Service2("B", SERVICE_B_LATENCY, user))
        for (i in 1..PERSISTENCE_FORK_FACTOR) {
            executor.execute(Persistence2(i, serviceA.get(), serviceB.get()))
        }
        latch.countDown()
    }
}

internal class Service2(private val name: String, private val latency: Long, private val iteration: Int) :
    Callable<String> {
    override fun call(): String {
        return service(name, latency, iteration)
    }
}

internal class Persistence2(private val fork: Int, private val serviceA: String, private val serviceB: String) :
    Runnable {
    override fun run() {
        persistence(fork, serviceA, serviceB)
    }
}
