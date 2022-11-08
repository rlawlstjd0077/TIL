package leetcode.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class FindMinimumInRotateeSortedArrayTest {
    @Test
    fun test() {
        val sut = FindMinimumInRotateeSortedArray()
        assertEquals(sut.findMin(intArrayOf(3,4,5,1,2)), 1)
        assertEquals(sut.findMin(intArrayOf(4,5,6,7,0,1,2)), 0)
        assertEquals(sut.findMin(intArrayOf(4,5,1,2,3)), 1)
    }
}