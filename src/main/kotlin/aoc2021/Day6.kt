package aoc2021

import aoc.ListOfNumbersBasedSolver

class Day6: ListOfNumbersBasedSolver(2021, 6) {

    override fun solvePart1(inputs: List<Int>) = inputs.countPopulationAfterDays(80)

    override fun solvePart2(inputs: List<Int>) = inputs.countPopulationAfterDays(256)

    companion object {
        fun List<Int>.countPopulationAfterDays(days: Int): Long {
            val numProducedFishInDays = LongArray(days)
            for(i in numProducedFishInDays.indices) {
                if(i == 0) {
                    numProducedFishInDays[i] = 0
                } else if(i <= 7) {
                    numProducedFishInDays[i] = 1
                } else if (i <= 9) {
                    numProducedFishInDays[i] = 2
                } else {
                    numProducedFishInDays[i] = 1 + numProducedFishInDays[i - 7] + numProducedFishInDays[i - 9]
                }
            }

            return size + sumOf { numProducedFishInDays[days - it] }
        }
    }

}

fun main() {
    Day6().verifyAndSolve("""
        3,4,3,1,2
    """.trimIndent(), 5934, 26984457539)
}