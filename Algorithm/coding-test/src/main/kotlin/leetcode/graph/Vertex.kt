package leetcode.graph


/**
 * @author Jay
 */
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

class Solution2 {
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

fun main() {

}