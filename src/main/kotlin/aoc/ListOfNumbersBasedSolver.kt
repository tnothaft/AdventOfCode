package aoc

abstract class ListOfNumbersBasedSolver(year: Int, day: Int): Solver<List<Int>>(year, day) {

    override fun splitInputs(inputs: String): List<Int> = inputs.split(",").map { it.toInt() }

}