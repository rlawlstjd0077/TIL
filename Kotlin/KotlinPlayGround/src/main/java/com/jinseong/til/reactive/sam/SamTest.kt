package com.jinseong.til.reactive.sam




fun samTest() {
    val list = listOf<String>()

    samExample({ it}, { it})
    samExample { it }
}

fun samExample(fun1: (Int) -> Int, fun2: (Int) -> Int) {

}

fun samExample(fun1: (Int) -> Int) {

}
