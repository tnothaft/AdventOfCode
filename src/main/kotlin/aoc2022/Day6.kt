package aoc2022

import aoc.CharacterBasedSolver

class Day6: CharacterBasedSolver(2022, 6) {

    override fun solvePart1(inputs: CharSequence): Any {
        inputs.windowed(4).forEachIndexed { index, s ->
            if(s.areAllCharsDifferent()) {
                return index+4
            }
        }
        return -1
    }

    override fun solvePart2(inputs: CharSequence): Any {
        inputs.windowed(14).forEachIndexed { index, s ->
            if(s.areAllCharsDifferent()) {
                return index+14
            }
        }
        return -1
    }

    companion object {

        fun String.areAllCharsDifferent(): Boolean {
            forEach {
                if(indexOf(it) != lastIndexOf(it))
                    return false
            }
            return true
        }

    }

}

fun main() {
    Day6().verifyAndSolve("""
        mjqjpqmgbljsphdztnvjfqwrcgsmlb
    """.trimIndent(), 7, 19)
}