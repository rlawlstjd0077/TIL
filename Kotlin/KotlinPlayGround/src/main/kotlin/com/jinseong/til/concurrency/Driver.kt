package com.jinseong.til.concurrency

fun main() {
    shouldBeNotConcurrent()
    shouldExecuteIterationsConcurrentlyNative()
    shouldExecuteIterationsConcurrently()
    shouldExecuteIterationsConcurrentlyForkJoinPool()
    shouldExecuteIterationsConcurrentlyCompletableFuture()
}
