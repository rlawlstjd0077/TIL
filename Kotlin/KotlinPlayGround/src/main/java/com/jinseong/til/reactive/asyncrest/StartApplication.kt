package com.jinseong.til.reactive.asyncrest

import org.reactivestreams.Publisher
import org.reactivestreams.Subscription
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.ZoneOffset
import java.util.*
import javax.annotation.PostConstruct


interface Sample {
    fun init() {
        println("afasfas")
    }
}

abstract class Sample1 {
    fun init() {
        println("afasfas")
    }
}

fun main() {
    val sample = object : Sample {}
    sample.init()

    val sample2 = object : Sample1() {}
    sample2.init()
}


