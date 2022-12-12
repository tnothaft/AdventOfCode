package aoc2022

import aoc.LineBasedSolver
import kotlin.math.absoluteValue
import kotlin.text.StringBuilder

class Day10: LineBasedSolver(2022, 10) {

    override fun solvePart1(inputs: List<String>): Any {
        var x = 1
        val xDuringCycle = mutableListOf<Int>(x)
        inputs.map {
            val instructionParts = it.split(' ')
            Pair(instructionParts[0], instructionParts.getOrNull(1)?.toIntOrNull())
        }.forEach {
            when(it.first) {
                "noop" -> xDuringCycle.add(x)
                "addx" -> {
                    xDuringCycle.add(x)
                    x += it.second!!
                    xDuringCycle.add(x)
                }
            }
        }

        return listOf(20, 60, 100, 140, 180, 220).sumOf { xDuringCycle.getSignalStrength(it) }
    }

    override fun solvePart2(inputs: List<String>): Any {
        var x = 1
        val xDuringCycle = mutableListOf<Int>(x)
        inputs.map {
            val instructionParts = it.split(' ')
            Pair(instructionParts[0], instructionParts.getOrNull(1)?.toIntOrNull())
        }.forEach {
            when(it.first) {
                "noop" -> xDuringCycle.add(x)
                "addx" -> {
                    xDuringCycle.add(x)
                    x += it.second!!
                    xDuringCycle.add(x)
                }
            }
        }

        val rows = StringBuilder()
        with(rows) {
            (0..5).forEach { row ->
                (0..39).forEach { col ->
                    if ((xDuringCycle[row * 40 + col] - col).absoluteValue <= 1) {
                        append('#')
                    } else {
                        append('.')
                    }
                }
                append('\n')
            }
        }
        println(rows)

        return 0
    }

    companion object {

        fun List<Int>.getSignalStrength(cycle: Int): Int {
            return cycle * this[cycle-1]
        }

    }

}

fun main() {
    Day10().verifyAndSolve("""
        addx 15
        addx -11
        addx 6
        addx -3
        addx 5
        addx -1
        addx -8
        addx 13
        addx 4
        noop
        addx -1
        addx 5
        addx -1
        addx 5
        addx -1
        addx 5
        addx -1
        addx 5
        addx -1
        addx -35
        addx 1
        addx 24
        addx -19
        addx 1
        addx 16
        addx -11
        noop
        noop
        addx 21
        addx -15
        noop
        noop
        addx -3
        addx 9
        addx 1
        addx -3
        addx 8
        addx 1
        addx 5
        noop
        noop
        noop
        noop
        noop
        addx -36
        noop
        addx 1
        addx 7
        noop
        noop
        noop
        addx 2
        addx 6
        noop
        noop
        noop
        noop
        noop
        addx 1
        noop
        noop
        addx 7
        addx 1
        noop
        addx -13
        addx 13
        addx 7
        noop
        addx 1
        addx -33
        noop
        noop
        noop
        addx 2
        noop
        noop
        noop
        addx 8
        noop
        addx -1
        addx 2
        addx 1
        noop
        addx 17
        addx -9
        addx 1
        addx 1
        addx -3
        addx 11
        noop
        noop
        addx 1
        noop
        addx 1
        noop
        noop
        addx -13
        addx -19
        addx 1
        addx 3
        addx 26
        addx -30
        addx 12
        addx -1
        addx 3
        addx 1
        noop
        noop
        noop
        addx -9
        addx 18
        addx 1
        addx 2
        noop
        noop
        addx 9
        noop
        noop
        noop
        addx -1
        addx 2
        addx -37
        addx 1
        addx 3
        noop
        addx 15
        addx -21
        addx 22
        addx -6
        addx 1
        noop
        addx 2
        addx 1
        noop
        addx -10
        noop
        noop
        addx 20
        addx 1
        addx 2
        addx 2
        addx -6
        addx -11
        noop
        noop
        noop
    """.trimIndent(), 13140, 0)
}