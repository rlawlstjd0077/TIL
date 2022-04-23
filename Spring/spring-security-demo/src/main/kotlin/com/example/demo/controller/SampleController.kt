package com.example.demo.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController


/**
 * @author Jay
 */
@RestController
class SampleController {
    @GetMapping("/primary")
    fun primary(): String {
        return "primary"
    }

    @GetMapping("/secondary")
    fun secondary(): String {
        return "secondary"
    }
}
