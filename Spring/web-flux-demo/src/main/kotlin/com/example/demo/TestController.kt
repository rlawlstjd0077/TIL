package com.example.demo

import org.apache.commons.logging.LogFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono


/**
 * @author Jay
 */
@RestController
class TestController(
    private val testService: TestService
) {
    val webClient = WebClient.create()

    @GetMapping("/rest")
    fun rest(idx: Int): Mono<String> {
        return webClient.get()
            .uri("http://localhost:8081/service?req={req}", idx.toString())
            .retrieve()
            .bodyToMono(String::class.java)
            .doOnNext{ log.info("first api") }
            .flatMap { Mono.fromCompletionStage(testService.service()) }
            .doOnNext{ log.info("my service") }
    }


    companion object {
        val log = LogFactory.getLog(TestController::class.java)
    }
}

