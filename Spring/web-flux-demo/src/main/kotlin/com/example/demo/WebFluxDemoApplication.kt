package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class WebFluxDemoApplication

fun main(args: Array<String>) {
	runApplication<WebFluxDemoApplication>(*args)
}
