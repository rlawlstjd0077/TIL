package com.jinseong.til.reactive.effective


/**
 * @author Jay
 */
fun main() {

}

val primes: Sequence<Int> = sequence {
    var numbers = generateSequence(2) { it + 1 }
    while(true) {
        val prime = numbers.first()
        println(prime)
        yield(prime)
        numbers = numbers.drop(1)
            .filter {
                println(prime)
                it % prime != 0
            }
        println(numbers.take(10).toList())
    }
}
