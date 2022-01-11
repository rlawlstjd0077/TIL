package com.jinseong.til.concurrency

import com.jinseong.til.concurrency.ConcurrencySupport.PERSISTENCE_FORK_FACTOR
import com.jinseong.til.concurrency.ConcurrencySupport.SERVICE_A_LATENCY
import com.jinseong.til.concurrency.ConcurrencySupport.SERVICE_B_LATENCY
import com.jinseong.til.concurrency.ConcurrencySupport.USERS
import com.jinseong.til.concurrency.ConcurrencySupport.persistence
import com.jinseong.til.concurrency.ConcurrencySupport.service
import com.jinseong.til.concurrency.ConcurrencySupport.start
import com.jinseong.til.concurrency.ConcurrencySupport.stop


fun main() {
    shouldExecuteIterationsConcurrentlyNative()
}

fun shouldExecuteIterationsConcurrentlyNative() {
    start()

    val threads: MutableList<Thread> = ArrayList()
    for (user in 1..USERS) {
        val thread = Thread(UserFlow(user))
        thread.start()
        threads.add(thread)
    }

    for (thread in threads) {
        thread.join()
    }

    stop()
}

internal class UserFlow(private val user: Int) : Runnable {
    private val serviceResult: MutableList<String> = ArrayList()

    override fun run() {
        val threadA = Thread(Service(this, "A", SERVICE_A_LATENCY, user))
        val threadB = Thread(Service(this, "B", SERVICE_B_LATENCY, user))
        threadA.start()
        threadB.start()
        threadA.join()
        threadB.join()
        val threads: MutableList<Thread> = ArrayList()
        for (i in 1..PERSISTENCE_FORK_FACTOR) {
            val thread = Thread(Persistence(i, serviceResult[0], serviceResult[1]))
            thread.start()
            threads.add(thread)
        }

        // Not the most optimal but gets the work done
        for (thread in threads) {
            thread.join()
        }
    }

    @Synchronized
    fun addToResult(result: String) {
        serviceResult.add(result)
    }
}

internal class Service(
    private val callback: UserFlow,
    private val name: String,
    private val latency: Long,
    private val iteration: Int
) :
    Runnable {
    override fun run() {
        callback.addToResult(service(name, latency, iteration))
    }
}

internal class Persistence(private val fork: Int, private val serviceA: String, private val serviceB: String) :
    Runnable {
    override fun run() {
        persistence(fork, serviceA, serviceB)
    }
}
