package aoc2021

import aoc.ListOfNumbersBasedSolver
import kotlin.math.abs
import kotlin.math.round

class Day7: ListOfNumbersBasedSolver(2021, 7) {

    override fun solvePart1(inputs: List<Int>): Int {
        val positionToMeet = inputs.sorted()[round(0.5 * inputs.size).toInt()]
        return inputs.sumOf { abs(positionToMeet - it) }
    }

    override fun solvePart2(inputs: List<Int>): Int {
        var minFuelConsumption = Int.MAX_VALUE
        for(i in inputs.min()..inputs.max()) {
            val fuelConsumption = inputs.sumOf { (0..abs(i-it)).sum() }
            if (fuelConsumption < minFuelConsumption) {
                minFuelConsumption = fuelConsumption
            }
        }
        return minFuelConsumption
    }

}

fun main() {
    Day7().verifyAndSolve("""
        16,1,2,0,4,2,7,1,2,14
    """.trimIndent(), 37, 168)
}