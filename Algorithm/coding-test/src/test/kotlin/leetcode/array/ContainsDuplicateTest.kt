package leetcode.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ContainsDuplicateTest {
    @Test
    fun test() {
        val sut = ContainsDuplicate()
        assertEquals(sut.containsDuplicate(intArrayOf(1,2,3,1)), true)
        assertEquals(sut.containsDuplicate(intArrayOf(1,2,3,4)), false)
        assertEquals(sut.containsDuplicate(intArrayOf(1,1,1,3,3,4,3,2,4,2)), true)
    }
}