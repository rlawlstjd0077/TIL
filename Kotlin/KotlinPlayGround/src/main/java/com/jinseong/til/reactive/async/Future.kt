package com.jinseong.til.reactive.async

import java.lang.RuntimeException
import java.util.concurrent.Callable
import java.util.concurrent.ExecutionException
import java.util.concurrent.Executors
import java.util.concurrent.FutureTask

class CallbackFutureTask(
    val callable: Callable<String>,
    val successCallback: (result: String) -> Unit,
    val exceptionCallback: (e: Throwable) -> Unit,
): FutureTask<String>(callable) {


    override fun done() {
        val str: String = ""

        str.hashCode()

        try { successCallback.invoke(get()) }
        catch (e: InterruptedException) {
            Thread.currentThread().interrupt()
        }
        catch (e: ExecutionException) {
            exceptionCallback.invoke(e.cause!!)
        }

    }
}

fun main() {
    val es = Executors.newCachedThreadPool()

    val task = CallbackFutureTask(
        {
            Thread.sleep(2000)
            if (1 == 1) {
                throw RuntimeException("Asunc Error")
            }
            println("Hello")
            "Hello1"
        },
        { println(it) },
        { println("Error: ${it.message}")}
    )

    es.submit(task)

    println("Exit")
}


