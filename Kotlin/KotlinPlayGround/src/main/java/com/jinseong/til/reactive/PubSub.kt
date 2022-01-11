package com.jinseong.til.reactive

import java.lang.RuntimeException
import java.util.concurrent.Executors
import java.util.concurrent.Flow
import java.util.concurrent.TimeUnit

class PubSub {
}

/**
 * Reactive Streams - JVM 공통 스펙
 */
fun main() {
    //Publisher <- Observerble
    //Subscriber <- Observer

    val list = listOf(1, 2, 3, 4, 5)

    val execeutor = Executors.newSingleThreadExecutor()

    val publisher = Flow.Publisher<Int> { subscriber ->
        val iterator = list.iterator()

        subscriber.onSubscribe(object : Flow.Subscription {
            override fun request(n: Long) {
                //비동기 작업 결과 객체
                execeutor.execute {
                    var counter = n
                    try {
                        while (counter-- > 0) {
                            if (iterator.hasNext()) {
                                subscriber.onNext(iterator.next())
                            } else {
                                subscriber.onComplete()
                                break
                            }
                        }
                    } catch (ex: RuntimeException) {
                        subscriber.onError(ex)
                    }
                }
            }

            override fun cancel() {
                TODO("Not yet implemented")
            }
        })
    }

    val subscriber: Flow.Subscriber<Int> = object : Flow.Subscriber<Int> {
        lateinit var subscription: Flow.Subscription


        override fun onSubscribe(subscription: Flow.Subscription) {
            this.subscription = subscription
            println("${Thread.currentThread().name}  onSubscribe")
            subscription.request(1)
        }

        override fun onNext(item: Int) {
            println("${Thread.currentThread().name} onNext $item")
            subscription.request(1)
        }

        override fun onError(throwable: Throwable) {
            println("onError")
        }

        override fun onComplete() {
            println("onComplete")
        }
    }
    publisher.subscribe(subscriber)
    execeutor.awaitTermination(10, TimeUnit.HOURS)
    execeutor.shutdown()
}
