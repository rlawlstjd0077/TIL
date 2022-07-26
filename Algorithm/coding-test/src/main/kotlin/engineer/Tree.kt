package engineer


/**
 * @author Jay
 */
data class Node(
    val value: Long,
    val left: Node?,
    val right: Node?
)

class Tree(
    val root: Node
) {

    fun retrieveInOrder(node: Node? = root, list: MutableList<Long>) {
        if (node != null) {
            retrieveInOrder(node.left, list)
            println(node.value)
            list += node.value
            retrieveInOrder(node.right, list)
        }
    }

    fun retrievePreOrder(node: Node? = root, list: MutableList<Long>) {
        if (node != null) {
            println(node.value)
            list += node.value
            retrievePreOrder(node.left, list)
            retrievePreOrder(node.right, list)
        }
    }

    fun retrievePostOrder(node: Node? = root, list: MutableList<Long>) {
        if (node != null) {
            retrievePostOrder(node.left, list)
            retrievePostOrder(node.right, list)
            println(node.value)
            list += node.value
        }
    }
}