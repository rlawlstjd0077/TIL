package leetcode.dp

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class ClimbingStairsTest {

    @Test
    fun `test`() {
        assertEquals(ClimbingStairs().climbStairs(1), 1)
        assertEquals(ClimbingStairs().climbStairs(2), 2)
        assertEquals(ClimbingStairs().climbStairs(3), 3)
        assertEquals(ClimbingStairs().climbStairs(4), 5)
        assertEquals(ClimbingStairs().climbStairs(5), 8)
    }
}