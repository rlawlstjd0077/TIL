package leetcode.graph

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class CourseScheduleTest {
    @Test
    fun `asfas`() {
        val solution = CourseSchedule()
        assertTrue(solution.canFinish(2, arrayOf(intArrayOf(1, 0))))
        assertFalse(solution.canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1))))
        assertTrue(solution.canFinish(1, arrayOf()))
        assertFalse(solution.canFinish(20, arrayOf(intArrayOf(0,10), intArrayOf(3, 18),intArrayOf(5,5),intArrayOf(6,11),intArrayOf(11,14),intArrayOf(13,1),intArrayOf(15,1),intArrayOf(17,4),)))
        assertFalse(solution.canFinish(20, arrayOf(intArrayOf(0,1), intArrayOf(1, 2),intArrayOf(2,3),intArrayOf(3,0))))
    }
}