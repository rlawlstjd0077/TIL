package leetcode.array


/**
 * @author Jay
 * - https://leetcode.com/problems/search-in-rotated-sorted-array/
 */
class SearchInRotatedSortedArray {
    fun search(nums: IntArray, target: Int): Int {
        return seachInternal(nums, 0, nums.size - 1, target)
    }

    private fun seachInternal(nums: IntArray, start: Int, end: Int, target: Int): Int {
        val midIdx = (start + end) / 2

        if (end - start <= 1) {
            var result = -1
            if (target == nums[start]) {
                result = start
            }
            if (target == nums[end]) {
                result = end
            }
            return result
        }

        return if(nums[start] < nums[midIdx]) {
            if (IntRange(nums[start], nums[midIdx]).contains(target)) seachInternal(nums, start, midIdx, target)
            else seachInternal(nums, midIdx, end, target)
        } else {
            if (IntRange(nums[midIdx], nums[end]).contains(target)) seachInternal(nums, midIdx, end, target)
            else seachInternal(nums, start, midIdx, target)
        }
    }
}