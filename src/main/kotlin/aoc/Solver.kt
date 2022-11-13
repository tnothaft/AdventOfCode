package aoc

import java.io.File

abstract class Solver<T>(val year: Int, val day: Int) {

    fun solve() {
        val inputs = splitInputs(File("data/${year}/${day}.txt").readText())
        println("Part 1: ${solvePart1(inputs)}")
        println("Part 2: ${solvePart2(inputs)}")
    }

    fun verifyAndSolve(verificationInputs: String, verificationResult1: Int, verificationResult2: Int) {
        val splitVerificationInputs = splitInputs(verificationInputs)
        val actualResult1 = solvePart1(splitVerificationInputs)
        if(verificationResult1 != actualResult1)
            error("Part 1 should result in $verificationResult1 but was $actualResult1")
        val actualResult2 = solvePart2(splitVerificationInputs)
        if(verificationResult2 != actualResult2)
            error("Part 2 should result in $verificationResult2 but was $actualResult2")

        solve()
    }

    abstract fun splitInputs(inputs: String): T

    abstract fun solvePart1(inputs: T): Int

    abstract fun solvePart2(inputs: T): Int

}