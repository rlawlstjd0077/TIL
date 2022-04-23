package com.toby.study.demo.components

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component


/**
 * @author Jay
 */
@Component
class SampleComponent {

    fun main() {
        val template = JdbcTemplate()
        template.queryForObject()
    }
}
