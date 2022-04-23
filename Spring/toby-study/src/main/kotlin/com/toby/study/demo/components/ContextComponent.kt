package com.toby.study.demo.components

import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.stereotype.Component


/**
 * @author Jay
 */
@Component
class ContextComponent: ApplicationContextAware {
    override fun setApplicationContext(applicationContext: ApplicationContext) {
        println()
    }
}
