package com.jinseong.test

import java.util.*

object SalesCostCalculator {
    fun calculate(list: List<String>): IntArray {
        val queue = LinkedList<Product>()
        val stack = LinkedList<Product>()

        var firstInFirstOutAmount = 0
        var lastInFirstOutAmount = 0

        list
            .map(ActionInfo::from)
            .forEach {
                if (it.action == "S") {
                    for (i in (1 .. it.quantity)) {
                        firstInFirstOutAmount += queue.poll().cost
                        lastInFirstOutAmount += stack.pop().cost
                    }
                } else {
                    for (i in (1 .. it.quantity)) {
                        queue.offer(Product(it.cost))
                        stack.push(Product(it.cost))
                    }
                }
            }

        return intArrayOf(firstInFirstOutAmount, lastInFirstOutAmount)
    }
}

data class Product(
    val cost: Int
)

data class ActionInfo(
    val action: String,
    val cost: Int,
    val quantity: Int,
) {
    companion object {
        fun from(string: String): ActionInfo {
            return ActionInfo(
                action = string.split(" ")[0],
                cost = string.split(" ")[1].toInt(),
                quantity = string.split(" ")[2].toInt(),
            )
        }
    }
}
