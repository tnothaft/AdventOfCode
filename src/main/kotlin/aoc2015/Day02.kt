package aoc2015

import aoc.LineBasedSolver
import aoc.readInputLines

class Day02: LineBasedSolver(2015, 2) {

    override fun solvePart1(inputs: List<String>): Int {
        return inputs.map {
            val measures = it.split("x").map { it.toInt() }
            2*(measures[0]*measures[1] + measures[1]*measures[2] + measures[2]*measures[0]) + measures[0]*measures[1]*measures[2]/measures.max()
        }.sum()
    }

    override fun solvePart2(inputs: List<String>): Int {
        return inputs.map {
            val measures = it.split("x").map { it.toInt() }
            2*(measures[0]+measures[1]+measures[2]-measures.max()) + measures[0]*measures[1]*measures[2]
        }.sum()
    }

}

fun main() {
    Day02().solve()
}