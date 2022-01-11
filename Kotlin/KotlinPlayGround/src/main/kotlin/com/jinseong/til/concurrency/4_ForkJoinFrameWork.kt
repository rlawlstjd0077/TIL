package com.jinseong.til.concurrency

import com.jinseong.til.concurrency.ConcurrencySupport.PERSISTENCE_FORK_FACTOR
import com.jinseong.til.concurrency.ConcurrencySupport.SERVICE_A_LATENCY
import com.jinseong.til.concurrency.ConcurrencySupport.SERVICE_B_LATENCY
import com.jinseong.til.concurrency.ConcurrencySupport.USERS
import com.jinseong.til.concurrency.ConcurrencySupport.persistence
import com.jinseong.til.concurrency.ConcurrencySupport.service
import com.jinseong.til.concurrency.ConcurrencySupport.start
import com.jinseong.til.concurrency.ConcurrencySupport.stop
import java.util.concurrent.ForkJoinPool
import java.util.concurrent.ForkJoinTask
import java.util.concurrent.RecursiveAction
import java.util.concurrent.TimeUnit
import java.util.stream.Collectors
import java.util.stream.IntStream


private val commonPool = ForkJoinPool(20)

fun main() {
    shouldExecuteIterationsConcurrentlyForkJoinPool()
}

fun shouldExecuteIterationsConcurrentlyForkJoinPool() {
    start()
    commonPool.submit(
        UserFlowRecursiveAction(
            IntStream.rangeClosed(1, USERS).boxed().collect(Collectors.toList())
        )
    )

    // Stop Condition
    commonPool.shutdown()
    commonPool.awaitTermination(60, TimeUnit.SECONDS)
    stop()
}


class UserFlowRecursiveAction(private val workload: MutableList<Int>) : RecursiveAction() {
    override fun compute() {
        if (workload.size > 1) {
            commonPool.submit(UserFlowRecursiveAction(workload.subList(1, workload.size)))
        }
        val user = workload[0]
        val taskA: ForkJoinTask<String> =
            commonPool.submit { service("A", SERVICE_A_LATENCY, user) } as ForkJoinTask<String>
        val taskB: ForkJoinTask<String> =
            commonPool.submit { service("B", SERVICE_B_LATENCY, user) } as ForkJoinTask<String>
        IntStream.rangeClosed(1, PERSISTENCE_FORK_FACTOR)
            .forEach { i: Int -> commonPool.submit { persistence(i, taskA.join(), taskB.join()) } }
    }
}
