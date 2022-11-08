package leetcode.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SearchInRotatedSortedArrayTest {
    @Test
    fun test() {
        val sut = SearchInRotatedSortedArray()
        assertEquals(sut.search(intArrayOf(4,5,6,7,0,1,2), 0), 4)
        assertEquals(sut.search(intArrayOf(4,5,6,7,0,1,2), 3), -1)
        assertEquals(sut.search(intArrayOf(1), 0), -1)
        assertEquals(sut.search(intArrayOf(1), 1), 0)
    }
}