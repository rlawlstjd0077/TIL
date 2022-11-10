package leetcode.array


/**
 * @author Jay
 * https://leetcode.com/problems/3sum/
 */
class ThreeSum {
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.size < 3) return emptyList()

        val results = mutableSetOf<List<Int>>()
        val negativeNums = mutableListOf<Int>()
        val positiveNums = mutableListOf<Int>()
        var zeroCount = 0

        nums.sorted().forEach {
            if (it < 0) {
                negativeNums += it
            } else if (it == 0) {
                zeroCount++
            } else {
                positiveNums += it
            }
        }

        if (zeroCount >= 3) {
            results += listOf(0, 0, 0)
        }

        //neg, zero, pos
        if (zeroCount > 0) {
            negativeNums
                .filter { positiveNums.contains(-it) }
                .forEach { results += listOf(it, 0, -it) }
        }

        results.addAll(calculate(negativeNums, positiveNums.toSet()))
        results.addAll(calculate(positiveNums, negativeNums.toSet()))
        return results.toList()
    }

    private fun calculate(source: List<Int>, dest: Set<Int>): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        for (i in source.indices) {
            for (j in i + 1 until source.size) {
                val targetValue = -(source[i] + source[j])
                if (dest.contains(targetValue)) {
                    result += listOf(source[i], source[j], targetValue)
                }
            }
        }
        return result
    }
}

