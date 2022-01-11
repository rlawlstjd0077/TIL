package com.jinseong.til.reactive

import org.reactivestreams.Subscriber
import org.reactivestreams.Subscription

open class DelegatingSub<T, R>(
    private val subscriber: Subscriber<in R>,
): Subscriber<T> {
    override fun onSubscribe(s: Subscription) {
        subscriber.onSubscribe(s)
    }

    override fun onNext(t: T) {
        subscriber.onNext(t as R)
    }

    override fun onError(t: Throwable) {
        subscriber.onError(t)
    }

    override fun onComplete() {
        subscriber.onComplete()
    }
}
