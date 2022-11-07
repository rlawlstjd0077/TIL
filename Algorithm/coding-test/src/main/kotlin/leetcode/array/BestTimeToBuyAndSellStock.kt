package leetcode.array

import kotlin.math.max


/**
 * @author Jay
 *
 * https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
 */
class BestTimeToBuyAndSellStock {
    fun maxProfit(prices: IntArray): Int {
        var minValue = prices[0]

        var maxProfit = 0

        for (i in 1 until prices.size) {
            val price = prices[i]
            if (minValue > price) {
                minValue = price
            } else {
                val profit = price - minValue
                if (maxProfit < profit) {
                    maxProfit = profit
                }
            }
        }

        return maxProfit
    }
}

fun main() {
    val sut = BestTimeToBuyAndSellStock()
    println(sut.maxProfit(intArrayOf(7,1,5,3,6,4)))
    println(sut.maxProfit(intArrayOf(7,6,4,3,1)))
}