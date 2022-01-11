package com.jinseong.test.com.jinseong.mockk

import com.jinseong.mockk.Input
import com.jinseong.mockk.Output
import com.jinseong.mockk.toOutput
import com.jinseong.test.SalesCostCalculator
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class ExtensionTest {

    @Test
    fun `Extension Function Mocking 테스트`() {
        val input = mockk<Input>()
        mockkStatic("com.jinseong.mockk.ExtensionsKt")
        every { input.toOutput(any()) } returns Output("title", "content")

        val output = input.toOutput("sss")
        expectThat(output.title) isEqualTo "title"
        expectThat(output.content) isEqualTo "content"
    }

    @Test
    fun `Object Mocking 테스트`() {
        SalesCostCalculator.calculate(listOf())
    }
}
