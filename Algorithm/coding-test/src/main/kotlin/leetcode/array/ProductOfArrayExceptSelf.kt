package leetcode.array

import java.util.*


/**
 * @author Jay
 * https://leetcode.com/problems/product-of-array-except-self/
 */
class ProductOfArrayExceptSelf {
    fun productExceptSelf(nums: IntArray): IntArray {
        val leftArr = IntArray(nums.size)
        nums
            .foldIndexed(1) { index, acc, i ->
                leftArr[index] = acc
                acc * i
            }
        val rightArr = IntArray(nums.size)
        nums.reversedArray()
            .foldIndexed(1) { index, acc, i ->
                rightArr[nums.size - 1 - index] = acc
                acc * i
            }

        return nums
            .mapIndexed { index, _ -> leftArr[index] * rightArr[index] }
            .toIntArray()
    }
}

fun main() {
    val sut = ProductOfArrayExceptSelf()
    println(sut.productExceptSelf(intArrayOf(1, 2, 3, 4)).contentToString())
}