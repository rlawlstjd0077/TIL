package com.jinseong.til.concurrency

import com.jinseong.til.concurrency.ConcurrencySupport.PERSISTENCE_FORK_FACTOR
import com.jinseong.til.concurrency.ConcurrencySupport.PERSISTENCE_LATENCY
import com.jinseong.til.concurrency.ConcurrencySupport.SERVICE_A_LATENCY
import com.jinseong.til.concurrency.ConcurrencySupport.SERVICE_B_LATENCY
import com.jinseong.til.concurrency.ConcurrencySupport.USERS
import com.jinseong.til.concurrency.ConcurrencySupport.persistence
import com.jinseong.til.concurrency.ConcurrencySupport.serviceA
import com.jinseong.til.concurrency.ConcurrencySupport.serviceB
import com.jinseong.til.concurrency.ConcurrencySupport.start
import com.jinseong.til.concurrency.ConcurrencySupport.stop


fun main() {


    shouldBeNotConcurrent()
}

fun shouldBeNotConcurrent() {
    start();

    for (i in 1 .. USERS) {
        val serviceA = serviceA(i)
        val serviceB = serviceB(i)

        for (j in 1 .. PERSISTENCE_FORK_FACTOR) {
            persistence(i, serviceA, serviceB)
        }
    }

    stop(
        threadCount = 1,
        expectedTime = USERS * (SERVICE_A_LATENCY + SERVICE_B_LATENCY + PERSISTENCE_LATENCY * PERSISTENCE_FORK_FACTOR).toInt()
    );
}
