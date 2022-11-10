package leetcode.array

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals


/**
 * @author Jay
 */
class ThreeSumTest {
    @Test
    fun test() {
        // given
        val sut = ThreeSum()
        assertEquals(sut.threeSum(intArrayOf(-1,0,1,2,-1,-4)), listOf(listOf(-1,0,1), listOf(-1,-1,2)))
        assertEquals(sut.threeSum(intArrayOf(0,1,1)), emptyList())
        assertEquals(sut.threeSum(intArrayOf(0,0,0)), listOf(listOf(0,0,0)))

        // when

        // then
    }
}