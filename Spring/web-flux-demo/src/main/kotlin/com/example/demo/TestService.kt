package com.example.demo

import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.util.concurrent.CompletableFuture


/**
 * @author Jay
 */
@Service
class TestService {
    @Async
    fun service(): CompletableFuture<String> {
        Thread.sleep(1000)
        return CompletableFuture.completedFuture("asfas")
    }
}
