package com.jinseong.test.tdd

import org.junit.Assert.*
import org.junit.Test
import org.springframework.context.support.beans
import java.util.*


/**
 * @author Jay
 */
class Money(
    open val amount: Int,
    open val currency: String
): Expression {
    override fun equals(other: Any?): Boolean {
        val money = other as Money
        return amount == money.amount && currency == money.currency
    }

    override operator fun plus(money: Expression): Expression {
        return Sum(this, money)
    }

    override fun times(multiplier: Int): Expression {
        return Money(amount * multiplier, currency)
    }

    override fun reduce(bank: Bank, to: String): Money {
        val rate = bank.rate(currency, to)
        return Money(amount / rate, to)
    }

    companion object {
        fun dollar(amount: Int): Money = Money(amount, "USD")
        fun franc(amount: Int): Money = Money(amount, "CHF")
    }
}

interface Expression {
    fun reduce(bank: Bank, to: String): Money
    fun plus(expression: Expression): Expression
    fun times(multiplier: Int): Expression
}

class Bank {
    private val rates = Hashtable<Pair, Int>()

    fun addRate(from: String, to: String, rate: Int) {
        rates[Pair(from, to)] = rate
    }
    fun reduce(source: Expression, to: String): Money {
        return source.reduce(this, to)
    }

    fun rate(from: String, to: String): Int {
        if (from == to) return 1
        return rates[Pair(from, to)]!!
    }
}

class Sum(
    val augend: Expression,
    val addend: Expression
): Expression {
    override fun reduce(bank: Bank, to: String): Money {
        val amount = augend.reduce(bank, to).amount + addend.reduce(bank, to).amount
        return Money(amount, to)
    }

    override fun plus(expression: Expression): Expression {
        TODO("Not yet implemented")
    }

    override fun times(multiplier: Int): Expression {
        return Sum(augend.times(multiplier), addend.times(multiplier))
    }
}

class Pair(
    val from: String,
    val to: String
) {
    override fun equals(other: Any?): Boolean {
        val pair = other as Pair
        return from == pair.from && to == pair.to
    }

    override fun hashCode(): Int {
        return 0
    }
}

internal class MoneyTest {

    @Test
    fun `testPlusSameCurrencyreturnsMoney`() {
        // given
        val sum = Money.dollar(1).plus(Money.dollar(1))

        assertTrue(sum is Money)
        // when

        // then
    }

    @Test
    fun `testSumPlusMoney`() {
        // given
        val fiveBucks = Money.dollar(5)
        val tenFranc = Money.franc(10)

        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val sum = Sum(fiveBucks, tenFranc).times(2)

        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(20), result)
    }

    @Test
    fun `testMixedAddition`() {
        val fiveBucks: Expression = Money.dollar(5)
        val tenFrancs: Expression = Money.franc(10)
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(fiveBucks.plus(tenFrancs), "USD")
        assertEquals(Money.dollar(10), result)
    }

    @Test
    fun `testReduceMoneyDifferentCurrency`() {
        val bank = Bank()
        bank.addRate("CHF", "USD", 2)
        val result = bank.reduce(Money.franc(2), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun `testReduceMone`() {
        val bank = Bank()
        val result = bank.reduce(Money.dollar(1), "USD")
        assertEquals(Money.dollar(1), result)
    }

    @Test
    fun `testReduceSum`() {
        val sum = Sum(Money.dollar(3), Money.dollar(4))
        val bank = Bank()
        val result = bank.reduce(sum, "USD")
        assertEquals(Money.dollar(7), result)
    }

    @Test
    fun `testPlusReturnSum`() {
        val five = Money.dollar(5)
        val result = five.plus(five)
        val sum = result as Sum
        assertEquals(five, sum.augend)
        assertEquals(five, sum.addend)
    }

    @Test
    fun `testMultiplication`() {
        // given
        val five: Money = Money.dollar(5)
        assertEquals(Money.dollar(10), five.times(2))
        assertEquals(Money.dollar(15), five.times(3))
    }

    @Test
    fun `testEquality`() {
        // given
        assertTrue(Money.dollar(5) == Money.dollar(5))
        assertFalse(Money.dollar(5) == Money.dollar(6))
        assertFalse(Money.franc(5) == Money.dollar(5))
    }

    @Test
    fun `testCurrency`() {
        // given
        assertEquals("USD", Money.dollar(1).currency)
        assertEquals("CHF", Money.franc(1).currency)
    }

}
