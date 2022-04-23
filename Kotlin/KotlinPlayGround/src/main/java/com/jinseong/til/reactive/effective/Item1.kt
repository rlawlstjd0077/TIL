package com.jinseong.til.reactive.effective

import kotlin.concurrent.thread
import kotlin.coroutines.coroutineContext


/**
 * @author Jay
 */
fun main() {
    sync()
}

fun first() {
    var num = 0
    for (i in 1 .. 1000) {
        thread {
            Thread.sleep(10)
            num += 1
        }
    }

    Thread.sleep(5000)
    println(num)
}

fun sync() {
    val lock = Any()
    var num = 0
    for (i in 1 .. 1000) {
        thread {
            Thread.sleep(10)
            println("Thread $i")
            synchronized(lock) {
                num += 1
            }
        }
    }

    Thread.sleep(1000)
    println(num)
}
