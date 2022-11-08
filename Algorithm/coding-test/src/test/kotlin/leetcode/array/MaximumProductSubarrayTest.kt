package leetcode.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MaximumProductSubarrayTest {
    @Test
    fun test() {
        val sut = MaximumProductSubarray()
        assertEquals(sut.maxProduct(intArrayOf(-1,-2,-3,0)), 6)
        assertEquals(sut.maxProduct(intArrayOf(-2,3,-4)), 24)
        assertEquals(sut.maxProduct(intArrayOf(2,3,-2,4)), 6)
        assertEquals(sut.maxProduct(intArrayOf(-2,0,-1)), 0)
    }
}