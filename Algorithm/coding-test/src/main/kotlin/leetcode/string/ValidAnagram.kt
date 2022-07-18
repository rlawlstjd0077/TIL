package leetcode.string


/**
 * 242. Valid Anagram (https://leetcode.com/problems/valid-anagram/)
 *
 * @author Jay
 */
class Solution4 {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        var aMap = mutableMapOf<Char, Int>()
        var bMap = mutableMapOf<Char, Int>()

        var set: MutableSet<Char> = mutableSetOf()

        for (i in s.indices) {
            aMap.addOrUpdate(s[i])
            bMap.addOrUpdate(t[i])
        }

        return (aMap.size == bMap.size) && aMap == bMap
    }
}

private fun MutableMap<Char, Int>.addOrUpdate(c: Char) {
    val value = get(c)?.plus(1) ?: 0
    put(c, value)
}

private fun MutableSet<Char>.removeOrAdd(c: Char) {
    remove(c).also {  removed ->
        if (!removed) {
            add(c)
        }
    }
}

fun main() {
    println(Solution4().isAnagram("aacc", "ccac"))
}