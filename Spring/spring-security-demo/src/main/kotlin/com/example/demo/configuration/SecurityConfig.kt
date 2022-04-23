package com.example.demo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.core.annotation.Order
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.server.SecurityWebFilterChain


/**
 * @author Jay
 */
@EnableWebSecurity
class SecurityConfig {

    @Bean
    @Order(1)
    fun primarySecurity(http: HttpSecurity): SecurityFilterChain {
        return http
            .antMatcher("/primary/**")
            .build()
    }

    @Bean
    @Order(2)
    fun secondarySecurity(http: HttpSecurity): SecurityFilterChain {
        return http
            .antMatcher("/secondary/**")
            .build()
    }
}
