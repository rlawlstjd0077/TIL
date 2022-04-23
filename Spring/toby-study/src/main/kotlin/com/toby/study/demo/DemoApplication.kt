package com.toby.study.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoApplication

fun main(args: Array<String>) {
	System.setProperty("server.port", "8081")
	runApplication<DemoApplication>(*args)
}
