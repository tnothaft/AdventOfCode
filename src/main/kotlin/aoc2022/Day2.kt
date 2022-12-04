package aoc2022

import aoc.LineBasedSolver

class Day2: LineBasedSolver(2022, 2) {

    private val scores = mapOf<String, Int>(
        "A" to 1,
        "B" to 2,
        "C" to 3,
        "X" to 1,
        "Y" to 2,
        "Z" to 3,
    )

    override fun solvePart1(inputs: List<String>): Any {
        var score = 0
        inputs.map {
            val shapes = it.split(" ").map { s -> scores[s] ?: 0 }
            Pair(shapes[0], shapes[1])
        }.forEach {
            score += it.second
            if(it.first == it.second) {
                score += 3
            } else if(it.second == it.first.mod(3) + 1) {
                score += 6
            }
        }
        return score
    }

    override fun solvePart2(inputs: List<String>): Any {
        var score = 0
        inputs.map {
            val shapes = it.split(" ").map { s -> scores[s] ?: 0 }
            Pair(shapes[0], shapes[1])
        }.forEach {
            score += when(it.second) {
                1 -> (it.first - 2).mod(3) + 1
                2 -> 3 + it.first
                3 -> 6 + it.first.mod(3) + 1
                else -> 0
            }
        }
        return score
    }

}

fun main() {
    Day2().verifyAndSolve("""
        A Y
        B X
        C Z
    """.trimIndent(), 15, 12)
}