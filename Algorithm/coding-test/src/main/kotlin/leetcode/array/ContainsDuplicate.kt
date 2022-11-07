package leetcode.array


/**
 * @author Jay
 * https://leetcode.com/problems/contains-duplicate/submissions/838656074/
 */
class ContainsDuplicate {
    fun containsDuplicate(nums: IntArray): Boolean {
        val set = HashSet<Int>(nums.size)

        nums.forEach {
            if (set.contains(it)) return true
            set.add(it)
        }

        return false
    }
}

fun main() {
    val sut = ContainsDuplicate()
    println(sut.containsDuplicate(intArrayOf(1,2,3,1)))
    println(sut.containsDuplicate(intArrayOf(1,2,3,4)))
}