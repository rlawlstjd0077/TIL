package com.jinseong.til.concurrency

import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors
import java.util.stream.IntStream



class X(
    var lastIdUsed: Int = 42
) {
  fun getNextId(): Int {
      return ++lastIdUsed
  }
}

fun main() {
    val xInstance = X()

    for (i in 0 .. 100) {
        println("====== $i 번째 실행 ========")
        xInstance.lastIdUsed = 42

        val thread1 = Thread { println("lastUsed: ${xInstance.getNextId()}") }
        val thread2 = Thread { println("lastUsed: ${xInstance.getNextId()}") }

        thread2.start()
        thread1.start()

        thread2.join()
        thread1.join()
        println()
    }
}
