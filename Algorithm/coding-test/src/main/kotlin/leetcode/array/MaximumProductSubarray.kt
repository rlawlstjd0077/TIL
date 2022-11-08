package leetcode.array


/**
 * @author Jay
 * https://leetcode.com/problems/maximum-product-subarray/
 */
class MaximumProductSubarray {
    fun maxProduct(nums: IntArray): Int {
        var max = nums[0]
        var min = nums[0]
        var result = nums[0]

        for (i in 1 until nums.size) {
            val it = nums[i]
            val tempMax = Math.max(it, Math.max(max * it, min * it))
            val tempMin = Math.min(it, Math.min(max * it, min * it))
            max = tempMax
            min = tempMin
            result = Math.max(result, max)
        }

        return result
    }
}

fun main() {

}