package con.jinseong.test

import com.jinseong.test.RectangleArranger
import org.junit.jupiter.api.Test
import strikt.api.expectThat
import strikt.assertions.isEqualTo

class RectangleArrangementTest {

    @Test
    fun test() {
        val input = arrayOf(
            intArrayOf(0, 2, 1, 3),
            intArrayOf(1, 2, 2, 5),
            intArrayOf(3, 3, 4, 4),
            intArrayOf(4, 1, 6, 3),
            intArrayOf(1, 6, 5, 7),
            intArrayOf(5, 5, 7, 6),
            intArrayOf(5, 8, 6, 10),
        )
        val process = RectangleArranger.arrange(input)

        expectThat(process) isEqualTo arrayOf("0 0 1 1", "1 0 2 3", "2 0 3 1", "3 0 5 2", "0 3 4 4", "2 2 4 3", "4 3 5 5")
    }

    @Test
    fun test1() {
        val intRange = 1..2
        expectThat(intRange) isEqualTo 1 .. 2
    }

    @Test
    fun test2() {
        val list = listOf("Kims1", "Kims2", "Kims3", "Kims4", "Kims1", "Kims2", "Kims3", "Kims4", "Kims1", "Kims2", "Kims3", "Kims4")

        list
            .chunked(4)
            .map {
                it.joinToString()
            }
            .forEach { println(it) }

        val name = "kim"
        "$name!".toList()

        list
            .chunked(4)
            .flatMap {
                listOf(it.joinToString()
                )
            }
            .forEach { println(it) }
    }
}
