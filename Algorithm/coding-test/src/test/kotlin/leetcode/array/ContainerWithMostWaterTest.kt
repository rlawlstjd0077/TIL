package leetcode.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ContainerWithMostWaterTest {
    @Test
    fun test() {
        // given

        val sut = ContainerWithMostWater()
        assertEquals(sut.maxArea(intArrayOf(1,8,6,2,5,4,8,3,7)), 49)
        assertEquals(sut.maxArea(intArrayOf(1,1)), 1)

        // when

        // then
    }
}