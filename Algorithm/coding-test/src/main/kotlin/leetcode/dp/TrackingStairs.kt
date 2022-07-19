package leetcode.dp


/**
 * 70. Climbing Stairs (https://leetcode.com/problems/climbing-stairs/)
 *
 * @author Jay
 */

class ClimbingStairs {
    fun climbStairs(n: Int): Int {
        if (n == 1) {
            return 1
        }

        val arr = IntArray(n)
        arr[0] = 1
        arr[1] = 2

        for (i in 2 until n) {
            arr[i] = arr[i - 1] + arr[i - 2]
        }

        return arr[n - 1]
    }

    private fun generateFatArr(n: Int, listSize: Int): IntArray {
        val needTwoSize = n - listSize
        return IntArray(listSize) { if (it < needTwoSize) { 2 } else { 1 } }
    }


    private fun calculatePermutationCount(fatArr: IntArray): Int {
        val resultSet = mutableSetOf<List<Int>>()
        perm(arr = fatArr, buffer = IntArray(fatArr.size), visited = BooleanArray(fatArr.size), results = resultSet, maxDepth = fatArr.size)
        return resultSet.size
    }

    fun perm(arr: IntArray, buffer: IntArray, visited: BooleanArray, results: MutableSet<List<Int>>, depth: Int = 0, maxDepth: Int) {
        if (depth == maxDepth) {
            results += buffer.toList()
            return
        }
        for (i in 0 until maxDepth) {
            if (!visited[i]) {
                visited[i] = true
                buffer[depth] = arr[i]
                perm(arr, buffer, visited, results, depth + 1, maxDepth)
                visited[i] = false
            }
        }
    }
}

fun main() {

}

