package aoc

abstract class LineBasedSolver(year: Int, day: Int): Solver<List<String>>(year, day) {

    override fun splitInputs(inputs: String): List<String> = inputs.lines()

}