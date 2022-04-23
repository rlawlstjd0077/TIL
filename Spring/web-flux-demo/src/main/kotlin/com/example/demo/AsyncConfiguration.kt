package com.example.demo

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.scheduling.annotation.AsyncConfigurer
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.Executor
import java.util.concurrent.Executors


/**
 * @author Jay
 */
@EnableAsync
@Configuration
class AsyncConfiguration: AsyncConfigurer {
    @Primary
    @Bean(name = [DEFAULT_ASYNC_EXECUTOR_NAME])
    override fun getAsyncExecutor(): Executor {
        return Executors.newFixedThreadPool(1000)
    }
    companion object {
        const val DEFAULT_ASYNC_EXECUTOR_NAME = "DefaultAsyncExecutor"
    }
}
