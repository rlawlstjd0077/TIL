package com.toby.study.demo

import org.junit.jupiter.api.AfterAll
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private lateinit var context: ApplicationContext

	@Test
	fun context() {
		println(context)
		Assertions.assertFalse(contextSet.contains(context))
		contextSet.add(context)
	}

	@Test
	fun context2() {
		Assertions.assertFalse(contextSet.contains(context))
		contextSet.add(context)
	}

	@Test
	fun context3() {
		Assertions.assertFalse(contextSet.contains(context))
		contextSet.add(context)
	}

	companion object {
		val contextSet: MutableSet<ApplicationContext> = mutableSetOf()
	}
}
