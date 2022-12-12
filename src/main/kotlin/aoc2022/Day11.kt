package aoc2022

import aoc.LineBasedSolver
import java.math.BigInteger
import java.util.LinkedList
import java.util.Queue

class Day11 : LineBasedSolver(2022, 11) {

    override fun solvePart1(inputs: List<String>): Any {
        val monkeys = inputs.windowed(6, 7).map {
            val id = it[0].substring(7, it[0].length - 1).toInt()
            val startingItems = it[1].substring(18).split(',').map { s -> s.trim().toInt() }
            val operation = it[2].substring(19)
            val testDivisibleBy = it[3].substring(21).toInt()
            val inCaseTrueThrowToMonkey = it[4].substring(29).toInt()
            val inCaseFalseThrowToMonkey = it[5].substring(30).toInt()
            Monkey(id, startingItems, operation, testDivisibleBy, inCaseTrueThrowToMonkey, inCaseFalseThrowToMonkey)
        }

        (1..20).forEach { _ ->
            monkeys.forEach {
                it.inspectAllItems(monkeys)
            }
        }

        return monkeys.map { it.numItemsInspected }.sortedDescending().subList(0, 2).reduce { acc, i -> acc * i }
    }

    override fun solvePart2(inputs: List<String>): Any {
        val monkeys = inputs.windowed(6, 7).map {
            val id = it[0].substring(7, it[0].length - 1).toInt()
            val startingItems = it[1].substring(18).split(',').map { s -> s.trim().toInt() }
            val operation = it[2].substring(19)
            val testDivisibleBy = it[3].substring(21).toInt()
            val inCaseTrueThrowToMonkey = it[4].substring(29).toInt()
            val inCaseFalseThrowToMonkey = it[5].substring(30).toInt()
            Monkey(id, startingItems, operation, testDivisibleBy, inCaseTrueThrowToMonkey, inCaseFalseThrowToMonkey)
        }

        val applyModWith = monkeys.map { it.testDivisibleBy }.reduce { acc, i -> acc * i }.toBigInteger()

        (1..10000).forEach { i ->
            if (i.mod(100) == 0) {
                println("Round $i")
            }
            monkeys.forEach {
                it.inspectAllItems(monkeys, false, applyModWith)
            }
            if(i == 20 || i == 1 || i == 1000) {
                monkeys.forEach { println("${it.numItemsInspected}") }
            }
        }

        return monkeys.map { it.numItemsInspected.toBigInteger() }.sortedDescending().subList(0, 2).reduce { acc, i -> acc * i }
    }

    companion object {

        class Monkey(
            private val id: Int,
            items: List<Int>,
            operation: String,
            val testDivisibleBy: Int,
            private val inCaseTrue: Int,
            private val inCaseFalse: Int
        ) {

            private val items: Queue<BigInteger> = LinkedList(items.map { it.toBigInteger() })
            private val operation = Operation(operation)
            var numItemsInspected = 0

            fun inspectAllItems(
                monkeys: List<Monkey>,
                reduceWorryLevels: Boolean = true,
                applyModWith: BigInteger = BigInteger.ZERO
            ) {
                while (!items.isEmpty()) {
                    inspectNextItem(monkeys, reduceWorryLevels, applyModWith)
                }
            }

            private fun inspectNextItem(monkeys: List<Monkey>, reduceWorryLevels: Boolean, applyModWith: BigInteger) {
                numItemsInspected++
                var item = items.remove()
                if (!reduceWorryLevels) {
                    item = item.mod(applyModWith)
                }
                item = operation.execute(item)
                if (reduceWorryLevels) {
                    item = (item / (3).toBigInteger())
                }
                if (item.mod(testDivisibleBy.toBigInteger()) == BigInteger.ZERO) {
                    monkeys[inCaseTrue].items.add(item)
                } else {
                    monkeys[inCaseFalse].items.add(item)
                }
            }

            override fun toString(): String {
                return "Monkey $id ($items)"
            }

        }

        class Operation(formula: String) {

            private val operationParts = formula.split(' ')

            fun execute(old: BigInteger): BigInteger {
                val firstValue = when (operationParts[0]) {
                    "old" -> old
                    else -> operationParts[0].toBigInteger()
                }
                val secondValue = when (operationParts[2]) {
                    "old" -> old
                    else -> operationParts[2].toBigInteger()
                }
                return when (operationParts[1]) {
                    "+" -> firstValue + secondValue
                    "*" -> firstValue * secondValue
                    else -> BigInteger.ZERO
                }
            }

        }

    }

}

fun main() {
    Day11().verifyAndSolve(
        """
        Monkey 0:
          Starting items: 79, 98
          Operation: new = old * 19
          Test: divisible by 23
            If true: throw to monkey 2
            If false: throw to monkey 3
        
        Monkey 1:
          Starting items: 54, 65, 75, 74
          Operation: new = old + 6
          Test: divisible by 19
            If true: throw to monkey 2
            If false: throw to monkey 0
        
        Monkey 2:
          Starting items: 79, 60, 97
          Operation: new = old * old
          Test: divisible by 13
            If true: throw to monkey 1
            If false: throw to monkey 3
        
        Monkey 3:
          Starting items: 74
          Operation: new = old + 3
          Test: divisible by 17
            If true: throw to monkey 0
            If false: throw to monkey 1
            """.trimIndent(), 10605, "2713310158".toBigInteger()
    )
}