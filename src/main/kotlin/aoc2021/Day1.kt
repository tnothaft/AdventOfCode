package aoc2021

import aoc.LineBasedSolver

class Day1: LineBasedSolver(2021, 1) {

    override fun solvePart1(inputs: List<String>): Int =
        inputs.map { it.toLong() }.zipWithNext().count { it.second > it.first }

    override fun solvePart2(inputs: List<String>): Int =
        inputs.map { it.toLong() }.windowed(3).zipWithNext().count { it.second[2] > it.first[0] }

}

fun main() {
    Day1().verifyAndSolve("""
        199
        200
        208
        210
        200
        207
        240
        269
        260
        263
    """.trimIndent(), 7, 5)
}