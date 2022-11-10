package leetcode.array

import kotlin.math.max


/**
 * @author Jay
 * https://leetcode.com/problems/container-with-most-water
 */
class ContainerWithMostWater {
    fun maxArea(height: IntArray): Int {
        var leftIdx = 0
        var rightIdx = height.size - 1
        var maxArea = 0
        while (leftIdx < rightIdx) {
            val currentWitdh = rightIdx - leftIdx
            val currentHeight = height[leftIdx].coerceAtMost(height[rightIdx])
            val currentArea = currentWitdh * currentHeight
            if (currentArea > maxArea) {
                maxArea = currentArea
            }

            if (height[leftIdx] < height[rightIdx]) leftIdx++ else rightIdx--
        }

        return maxArea
    }
}