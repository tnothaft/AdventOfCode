package aoc2022

import aoc.LineBasedSolver

class Day1: LineBasedSolver(2022, 1) {

    override fun solvePart1(inputs: List<String>): Any {
        var max = 0
        var current = 0
        inputs.forEach {
            if(it.isEmpty()) {
                max = maxOf(max, current)
                current = 0
            } else {
                current += it.toInt()
            }
        }
        max = maxOf(max, current)
        return max
    }

    override fun solvePart2(inputs: List<String>): Any {
        val amountSnacks = ArrayList<Int>()
        var current = 0
        inputs.forEach {
            if(it.isEmpty()) {
                amountSnacks.add(current)
                current = 0
            } else {
                current += it.toInt()
            }
        }
        amountSnacks.add(current)
        amountSnacks.sortDescending()
        return amountSnacks[0] + amountSnacks[1] + amountSnacks[2]
    }
}

fun main() {
    Day1().verifyAndSolve("""
        1000
        2000
        3000

        4000

        5000
        6000

        7000
        8000
        9000

        10000
    """.trimIndent(), 24000, 45000)
}