package leetcode.array


/**
 * @author Jay
 * https://leetcode.com/problems/find-minimum-in-rotated-sorted-array/
 */
class FindMinimumInRotateeSortedArray {

    fun findMin(nums: IntArray): Int {
        if (nums.first() < nums.last()) return nums.first()
        return binarySearch(nums, 0, nums.size - 1)
    }

    fun binarySearch(nums: IntArray, start: Int, end: Int): Int {
        println("start: $start, end: $end")
        val midIdx = (start + end) / 2

        if (end - start <= 1) {
            return nums[start].coerceAtMost(nums[end])
        }

        return if (nums[midIdx] < nums[start]) {
            binarySearch(nums, start, midIdx)
        } else {
            binarySearch(nums, midIdx, end)
        }
    }
}