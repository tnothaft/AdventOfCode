package aoc2015

import aoc.CharacterBasedSolver
import aoc.readInputs
import aoc2015.Day03.Companion.move

class Day03: CharacterBasedSolver(2015, 3) {

    override fun solvePart1(inputs: CharSequence): Int {
        val visitedHouses = ArrayList<Pair<Int, Int>>()
        var coords = Pair(0, 0)
        visitedHouses.add(coords)
        inputs.forEach {
            coords = coords.move(it)
            if(!visitedHouses.contains(coords))
                visitedHouses.add(coords)
        }
        return visitedHouses.count()
    }

    override fun solvePart2(inputs: CharSequence): Int {
        val visitedHouses = ArrayList<Pair<Int, Int>>()
        var santaCoords = Pair(0, 0)
        var roboCoords = Pair(0, 0)
        var santasTurn = true
        visitedHouses.add(santaCoords)
        inputs.forEach {
            val coord =  if(santasTurn) {
                santaCoords = santaCoords.move(it)
                santaCoords
            } else {
                roboCoords = roboCoords.move(it)
                roboCoords
            }
            if(!visitedHouses.contains(coord))
                visitedHouses.add(coord)
            santasTurn = !santasTurn
        }
        return visitedHouses.count()
    }

    companion object {

        fun Pair<Int, Int>.move(c: Char): Pair<Int, Int> =
            when(c) {
                '^' -> Pair(first, second + 1)
                '>' -> Pair(first + 1, second)
                'v' -> Pair(first, second - 1)
                '<' -> Pair(first - 1, second)
                else -> Pair(first, second)
            }
        
    }
}

fun main() {
    Day03().solve()
}