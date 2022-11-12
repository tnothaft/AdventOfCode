package aoc

import java.io.File

abstract class Solver<T>(val year: Int, val day: Int) {

    fun solve() {
        val inputs = splitInputs(File("data/${year}/${day}_inputs.txt").readText())
        println("Part 1: ${solvePart1(inputs)}")
        println("Part 2: ${solvePart2(inputs)}")
    }

    abstract fun splitInputs(inputs: String): T

    abstract fun solvePart1(inputs: T): Int

    abstract fun solvePart2(inputs: T): Int

}