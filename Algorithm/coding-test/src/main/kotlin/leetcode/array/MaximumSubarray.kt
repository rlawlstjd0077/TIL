package leetcode.array


/**
 * @author Jay
 * https://leetcode.com/problems/maximum-subarray/
 */
class MaximumSubarray {
    fun maxSubArray(nums: IntArray): Int {
        var max = nums[0]
        var local_max = nums[0]

        for (i in 1 until nums.size) {
            local_max = Math.max(local_max + nums[i], nums[i])
            max = local_max.coerceAtLeast(max)
        }

        return max
    }
}

fun main() {
    val sut = MaximumSubarray()
    println(sut.maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4)))
}