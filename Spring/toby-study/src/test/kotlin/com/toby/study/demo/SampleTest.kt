package com.toby.study.demo

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest


/**
 * @author Jay
 */

@SpringBootTest
class SampleTest {

    @BeforeEach
    fun setUp() {
        throw IllegalArgumentException("Asfas")
    }

    @Test
    fun debugTest() {
        println("asfas")
    }
}
