package com.jinseong

import com.jinseong.test.SalesCostCalculator
import org.junit.jupiter.api.Test

class SalesCostCalculatorTest {

    @Test
    fun test() {
        val input = listOf("P 300 6", "P 500 3", "S 1000 4", "P 600 2", "S 1200 1")

        val result = SalesCostCalculator.calculate(input)

        //expectThat(result) isEqualTo intArrayOf(1500, 2400)
    }

    @Test
    fun test1() {
        val input = listOf("P 100 4", "P 300 9", "S 1000 7", "P 1000 8", "S 700 7", "S 700 3")

        val result = SalesCostCalculator.calculate(input)

//        expectThat(result) isEqualTo intArrayOf(7100, 10700)
    }

    @Test
    fun test2() {
        val input = listOf("P 300 6", "P 500 3", "S 1000 4", "P 600 1", "S 1200 2")

        val result = SalesCostCalculator.calculate(input)

//        expectThat(result) isEqualTo intArrayOf(1800, 2700)
    }
}
