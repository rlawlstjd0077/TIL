package engineer

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class TreeTest {
    @Test
    fun `tree 탐색 테스트`() {
        // given
        val nodeN4 = Node(4, null, null)
        val nodeN5 = Node(5, null, null)
        val nodeN2 = Node(2, nodeN4, nodeN5)
        val nodeN3 = Node(3, null, null)
        val root = Node(1, nodeN2, nodeN3)
        val tree = Tree(root)

        // when
        val inOrderList = mutableListOf<Long>()
        tree.retrieveInOrder(list = inOrderList)
        println()

        val preOrderList = mutableListOf<Long>()
        tree.retrievePreOrder(list = preOrderList)
        println()

        val postOrderList = mutableListOf<Long>()
        tree.retrievePostOrder(list = postOrderList)

        // then
        assertEquals(inOrderList, listOf<Long>(4,2,5,1,3))
        assertEquals(preOrderList, listOf<Long>(1,2,4,5,3))
        assertEquals(postOrderList, listOf<Long>(4,5,2,3,1))
    }
}