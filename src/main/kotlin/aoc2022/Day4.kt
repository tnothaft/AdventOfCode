package aoc2022

import aoc.LineBasedSolver

class Day4: LineBasedSolver(2022, 4) {

    override fun solvePart1(inputs: List<String>): Any {
        return inputs.map {
            val assignedSections = it.split(",")
            val assignedSection1 = assignedSections[0].split("-").map { x -> x.toInt() }
            val assignedSection2 = assignedSections[1].split("-").map { x -> x.toInt() }
            Pair(Pair(assignedSection1[0], assignedSection1[1]), Pair(assignedSection2[0], assignedSection2[1]))
        }.count {
            (it.first.first <= it.second.first && it.first.second >= it.second.second) ||
                    (it.second.first <= it.first.first && it.second.second >=  it.first.second)
        }
    }

    override fun solvePart2(inputs: List<String>): Any {
        return inputs.map {
            val assignedSections = it.split(",")
            val assignedSection1 = assignedSections[0].split("-").map { x -> x.toInt() }
            val assignedSection2 = assignedSections[1].split("-").map { x -> x.toInt() }
            Pair(Pair(assignedSection1[0], assignedSection1[1]), Pair(assignedSection2[0], assignedSection2[1]))
        }.count {
            (it.first.first <= it.second.first && it.first.second >= it.second.first) ||
                    (it.second.first <= it.first.first && it.second.second >= it.first.first)
        }
    }

}

fun main() {
    Day4().verifyAndSolve("""
        2-4,6-8
        2-3,4-5
        5-7,7-9
        2-8,3-7
        6-6,4-6
        2-6,4-8
    """.trimIndent(), 2, 4)
}