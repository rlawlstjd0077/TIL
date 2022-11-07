package leetcode.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MaximumSubarrayTest {
    @Test
    fun test() {
        val sut = MaximumSubarray()
        assertEquals(sut.maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4)), 6)
        assertEquals(sut.maxSubArray(intArrayOf(1)), 1)
        assertEquals(sut.maxSubArray(intArrayOf(5,4,-1,7,8)), 23)
    }
}