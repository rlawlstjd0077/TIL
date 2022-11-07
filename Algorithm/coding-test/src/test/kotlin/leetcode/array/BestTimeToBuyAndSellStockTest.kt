package leetcode.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class BestTimeToBuyAndSellStockTest {
    @Test
    fun test() {
        val sut = BestTimeToBuyAndSellStock()
        assertEquals(sut.maxProfit(intArrayOf(7,1,5,3,6,4)), 5)
        assertEquals(sut.maxProfit(intArrayOf(7,6,4,3,1)), 0)
        assertEquals(sut.maxProfit(intArrayOf(2, 4, 1)), 2)
    }
}