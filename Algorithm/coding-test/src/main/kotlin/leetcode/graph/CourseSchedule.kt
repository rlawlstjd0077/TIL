package leetcode.graph

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

data class Vertex(
    val index: Int
) {
    var isVisited: Boolean = false
    var isBeingVisit: Boolean = false
    var adjencyList: List<Vertex> = mutableListOf()
}

class Graph {
    var vertexes: MutableList<Vertex> = mutableListOf()

    fun addEdge(prev: Vertex, next: Vertex) {
        val source = getOrCreate(prev)
        val dest = getOrCreate(next)

        source.adjencyList += dest
    }

    private fun getOrCreate(vertex: Vertex): Vertex {
        return vertexes.find { it == vertex } ?: vertex.also { vertexes += it }
    }

    fun hasCycle(): Boolean {
        vertexes.forEach { vertex ->
            if (!vertex.isVisited && hasCycle(vertex)) {
                return true
            }
        }

        return false
    }

    fun hasCycle(vertex: Vertex): Boolean {
        vertex.isBeingVisit = true

        for (neighbor in vertex.adjencyList) {
            if (neighbor.isBeingVisit) {
                return true
            }

            if (!neighbor.isVisited && hasCycle(neighbor)) {
                return true
            }
        }

        vertex.isBeingVisit = false
        vertex.isVisited = true
        return false
    }
}

class CourseSchedule {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val graph = Graph()

        prerequisites.forEach {
            val prev = Vertex(it[0])
            val next = Vertex(it[1])
            graph.addEdge(prev, next)
        }

        return !graph.hasCycle()
    }
}


