package leetcode.graph

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.util.*
import java.util.concurrent.ArrayBlockingQueue


/**
 * 207. Course Schedule (https://leetcode.com/problems/course-schedule/)
 *
 * @author Jay
 */
class Solution {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        if (prerequisites.isEmpty()) {
            return true
        }

        val alreadyQueue: ArrayBlockingQueue<Segment> = ArrayBlockingQueue(prerequisites.size)
        val courseList = (0 until numCourses).toMutableList()

        val prerequisitesList = prerequisites.map { Segment(it[0], it[1]) }.toMutableList()

        prerequisitesList.forEach { course ->
            if (alreadyQueue.any { it.shouldBe == course.target && it.target == course.shouldBe}) {
                return false
            }

            alreadyQueue.add(course)
        }
        return true
    }
}

data class Segment(
    val target: Int,
    val shouldBe: Int
)

class Solution1 {


    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        var courseMap: Map<Int, MutableList<Int>> = mutableMapOf()

        prerequisites
            .asSequence()
            .map { PreRequisite(it[0], it[1]) }
            .forEach {
                if (courseMap.containsKey(it.target)) {
                    courseMap.getValue(it.target) += it.shouldBe
                } else {
                    courseMap += it.target to mutableListOf(it.shouldBe)
                }
            }

        val visited = BooleanArray(numCourses)
        val checked = BooleanArray(numCourses)

        for (key in 0 until numCourses) {
            if (isCycle(courseMap, key, visited, checked)) {
                return false
            }
        }
        return true
    }

    private fun isCycle(courseMap: Map<Int, MutableList<Int>>, currentCourse: Int, visited: BooleanArray, checked: BooleanArray): Boolean {
        if (checked[currentCourse]) return false
        if (visited[currentCourse]) return true

        if (!courseMap.containsKey(currentCourse)) {
            return false
        }

        visited[currentCourse] = true

        var result = false
        for(shouldBe in courseMap.getValue(currentCourse)) {
            result = isCycle(courseMap, shouldBe, visited, checked)
            if (result) break
        }

        visited[currentCourse] = false
        checked[currentCourse] = true
        return result
    }
}

data class PreRequisite(
    val target: Int,
    val shouldBe: Int
)

//class SolutionTest {
//    @Test
//    fun `asfas`() {
//        val solution = Solution2()
//        expectThat(solution.canFinish(2, arrayOf(intArrayOf(1, 0)))) isEqualTo true
//        //expectThat(solution.canFinish(2, arrayOf(intArrayOf(1, 0), intArrayOf(0, 1)))) isEqualTo false
//        expectThat(solution.canFinish(1, arrayOf())) isEqualTo true
//        expectThat(solution.canFinish(20, arrayOf(intArrayOf(0,10), intArrayOf(3, 18),intArrayOf(5,5),intArrayOf(6,11),intArrayOf(11,14),intArrayOf(13,1),intArrayOf(15,1),intArrayOf(17,4),))) isEqualTo false
//
//        expectThat(solution.canFinish(20, arrayOf(intArrayOf(0,1), intArrayOf(1, 2),intArrayOf(2,3),intArrayOf(3,0)))) isEqualTo false
//    }
//}

fun main() {
    val now = LocalDate.now()

    //println(now.atStartOfDay())
    //println(now.atStartOfDay().toUtc(TimeZone.getDefault().rawOffset))
}

fun LocalDateTime.toUtc(offset: ZoneOffset): LocalDateTime =
    this.atZone(offset).withZoneSameInstant(ZoneOffset.UTC).toLocalDateTime()