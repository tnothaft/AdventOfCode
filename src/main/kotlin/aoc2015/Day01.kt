package aoc2015

import aoc.CharacterBasedSolver
import aoc.readInputs

class Day01: CharacterBasedSolver(2015, 1) {

    override fun solvePart1(inputs: CharSequence): Int =
        inputs.count { it == '(' } - inputs.count { it == ')' }

    override fun solvePart2(inputs: CharSequence): Int {
        var floor = 0
        var instruction = 1
        inputs.forEach {
            when (it) {
                '(' -> floor++
                ')' -> floor--
            }
            if (floor == -1)
                return instruction
            instruction++
        }
        return Int.MAX_VALUE
    }
}

fun main() {
    Day01().solve()
}