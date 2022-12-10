package aoc2022

import aoc.LineBasedSolver
import kotlin.math.abs

class Day9: LineBasedSolver(2022, 9) {

    override fun solvePart1(inputs: List<String>): Any {
        val rope = Rope(2)
        inputs.map {
            val directions = it.split(' ')
            Pair(directions[0][0], directions[1].toInt())
        }.forEach {
            for(i in 1..it.second) {
                when (it.first) {
                    'U' -> rope.move(0, 1)
                    'D' -> rope.move(0, -1)
                    'R' -> rope.move(1, 0)
                    'L' -> rope.move(-1, 0)
                }
            }
        }
        return rope.getNumberOfVisitedPositions()
    }

    override fun solvePart2(inputs: List<String>): Any {
        val rope = Rope(10)
        inputs.map {
            val directions = it.split(' ')
            Pair(directions[0][0], directions[1].toInt())
        }.forEach {
            for(i in 1..it.second) {
                when (it.first) {
                    'U' -> rope.move(0, 1)
                    'D' -> rope.move(0, -1)
                    'R' -> rope.move(1, 0)
                    'L' -> rope.move(-1, 0)
                }
            }
        }
        return rope.getNumberOfVisitedPositions()
    }

    companion object {

        class Rope(private val numberOfKnots: Int) {

            private val knots = mutableListOf<Pair<Int, Int>>()
            init {
                for(i in 0 until numberOfKnots) {
                    knots.add(i, Pair(0, 0))
                }
            }
            private val positionsVisitedByTail = mutableListOf(Pair(0, 0))

            private fun markVisitedPosition() {
                if (!positionsVisitedByTail.contains(knots[knots.size-1])) {
                    positionsVisitedByTail.add(knots[knots.size-1])
                }
            }

            fun getNumberOfVisitedPositions(): Int {
                return positionsVisitedByTail.size
            }

            fun move(moveX: Int, moveY: Int) {
                knots[0] = Pair(knots[0].first+moveX, knots[0].second+moveY)
                for(i in 1 until numberOfKnots) {
                    knots[i] = knots[i].getNewPosition(knots[i-1].first, knots[i-1].second)
                }
                markVisitedPosition()
            }
        }

        fun Pair<Int, Int>.getNewPosition(x: Int, y: Int): Pair<Int, Int> {
            if(abs(x - first) <= 1 && abs(y - second) <= 1) {
                return this
            }

            val diffX = x - first
            val newX = first + when {
                diffX > 0 -> 1
                diffX < 0 -> -1
                else -> 0
            }

            val diffY = y - second
            val newY = second + when {
                diffY > 0 -> 1
                diffY < 0 -> -1
                else -> 0
            }

            return Pair(newX, newY)
        }

    }

}

fun main() {
    Day9().verifyAndSolve("""
        R 5
        U 8
        L 8
        D 3
        R 17
        D 10
        L 25
        U 20
    """.trimIndent(), 88, 36)
}