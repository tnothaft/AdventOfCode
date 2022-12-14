package aoc

import java.io.File

abstract class Solver<T>(val year: Int, val day: Int) {

    fun solve() {
        val inputs = splitInputs(File("data/${year}/${day}.txt").readText())
        println("Part 1: ${solvePart1(inputs)}")
        println("Part 2: ${solvePart2(inputs)}")
    }

    fun verifyAndSolve(verificationInputs: String, verificationResult1: Any, verificationResult2: Any) {
        val splitVerificationInputs = splitInputs(verificationInputs)
        val actualResult1 = solvePart1(splitVerificationInputs)
        if(verificationResult1.toString() != actualResult1.toString())
            error("Part 1 should result in $verificationResult1 but was $actualResult1")
        val actualResult2 = solvePart2(splitVerificationInputs)
        if(verificationResult2.toString() != actualResult2.toString())
            error("Part 2 should result in $verificationResult2 but was $actualResult2")

        solve()
    }

    abstract fun splitInputs(inputs: String): T

    abstract fun solvePart1(inputs: T): Any

    abstract fun solvePart2(inputs: T): Any

}