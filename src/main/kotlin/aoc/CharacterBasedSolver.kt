package aoc

abstract class CharacterBasedSolver(year: Int, day: Int): Solver<CharSequence>(year, day) {

    override fun splitInputs(inputs: String): CharSequence = inputs

}