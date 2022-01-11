package com.jinseong.til.reactive

import org.reactivestreams.Publisher
import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription
import java.util.stream.Stream
import kotlin.streams.toList

class PubSub2 {
}


fun main() {
    val publisher = iterPub(Stream.iterate(1) { it + 1 }.limit(10).toList())

    val mapPub = mapPub(publisher) { "[$it]" }
    //val reducePub = reducePub(publisher, 0) {a, b -> a + b}

    mapPub.subscribe(logSub())
}

/*private fun reducePub(
    publisher: Publisher<Int>,
    initValue: Int,
    function: (Int, Int) -> Int
):Publisher<Int> {
    return object : Publisher<Int> {
        override fun subscribe(sub: Subscriber<in Int> ) {
            publisher.subscribe(object : DelegatingSub(sub) {
                var result = initValue

                override fun onNext(t: Int) {
                    result = function.invoke(result, t)
                }

                override fun onComplete() {
                    sub.onNext(result)
                    sub.onComplete()
                }
            })
        }
    }
}*/

//T -> R
private fun <T, R> mapPub(
    publisher: Publisher<T>,
    function: (T) -> R
): Publisher<R> {
    return object : Publisher<R> {
        override fun subscribe(sub: Subscriber<in R>) {
            publisher.subscribe(object : DelegatingSub<T, R>(sub) {
                override fun onNext(t: T) {
                    sub.onNext(function.invoke(t))
                }
            })
        }
    }
}

private fun <T>logSub() = object : Subscriber<T> {
    override fun onSubscribe(s: Subscription) {
        println("onSubscribe")
        s.request(Long.MAX_VALUE)
    }

    override fun onNext(t: T) {
        println("onNext: $t")
    }

    override fun onError(t: Throwable) {
    }

    override fun onComplete() {
        println("onComplete")
    }
}

private fun iterPub(iterator: List<Int>) = Publisher<Int> { sub ->
    sub.onSubscribe(object : Subscription {
        override fun request(n: Long) {
            iterator.forEach { sub.onNext(it) }
            sub.onComplete()
        }

        override fun cancel() {
        }
    })
}
