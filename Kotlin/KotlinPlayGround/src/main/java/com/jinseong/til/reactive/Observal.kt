package com.jinseong.til.reactive

import java.util.*
import java.util.concurrent.Executors

class Reactive {
    fun iter() {
        val iterable = Iterable {
            object : Iterator<Int> {

                var i= 0
                val MAX = 10

                override fun hasNext(): Boolean {
                    return i < 10
                }

                override fun next(): Int {
                    return ++i
                }
            }
        }

        for (i in iterable) {
            println(i)
        }
    }

}
@SuppressWarnings("deprecation")
fun main() {
    val intObservable = IntObservable()
    intObservable.addObserver { o, arg -> println(Thread.currentThread().name + " " + arg) }

    val newSingleThreadExecutor = Executors.newSingleThreadExecutor()
    newSingleThreadExecutor.execute(intObservable)

    println(Thread.currentThread().name + " EXIT")
    newSingleThreadExecutor.shutdown()
}

class IntObservable: Observable(), Runnable {
    override fun run() {
        for (i in 1 .. 10) {
            setChanged()
            notifyObservers(i)
        }
    }
}

