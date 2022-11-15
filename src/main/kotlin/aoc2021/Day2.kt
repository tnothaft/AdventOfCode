package aoc2021

import aoc.LineBasedSolver

class Day2: LineBasedSolver(2021, 2) {

    override fun solvePart1(inputs: List<String>): Int {
        var coord = Pair(0, 0)
        inputs.forEach { coord = coord.move(it.extractCommand()) }
        return coord.first * coord.second
    }

    override fun solvePart2(inputs: List<String>): Int {
        var coord = Triple(0, 0, 0)
        inputs.forEach { coord = coord.move(it.extractCommand()) }
        return coord.first * coord.second
    }

    companion object {

        fun String.extractCommand(): Pair<String, Int> {
            val parts = this.split(" ")
            return Pair(parts[0], parts[1].toInt())
        }

        fun Pair<Int, Int>.move(command: Pair<String, Int>): Pair<Int, Int> =
            when(command.first) {
                "forward" -> Pair(first + command.second, second)
                "down" -> Pair(first, second + command.second)
                "up" -> Pair(first, second - command.second)
                else -> Pair(first, second)
            }

        fun Triple<Int, Int, Int>.move(command: Pair<String, Int>): Triple<Int, Int, Int> =
            when(command.first) {
                "forward" -> Triple(first + command.second, second + third*command.second, third)
                "down" -> Triple(first, second, third + command.second)
                "up" -> Triple(first, second, third - command.second)
                else -> Triple(first, second, third)
            }

    }

}

fun main() {
    Day2().verifyAndSolve("""
        forward 5
        down 5
        forward 8
        up 3
        down 8
        forward 2
    """.trimIndent(), 150, 900)
}