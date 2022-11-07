package leetcode.array

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.util.*

internal class ProductOfArrayExceptSelfTest {
    @Test
    fun test() {
        val sut = ProductOfArrayExceptSelf()

        assertEquals(Arrays.equals(sut.productExceptSelf(intArrayOf(1, 2, 3, 4)), intArrayOf(24,12,8,6)), true)
    }
}