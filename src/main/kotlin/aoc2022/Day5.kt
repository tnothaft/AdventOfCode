package aoc2022

import aoc.LineBasedSolver

class Day5: LineBasedSolver(2022, 5) {

    override fun solvePart1(inputs: List<String>): Any {
        val splitAt = inputs.indexOf("")
        val stackPositions = inputs.subList(0, splitAt-1).map {
            it.windowed(3, 4).map { c -> c[1] }
        }
        val numberOfStacks = stackPositions[stackPositions.size-1].size
        val moves = inputs.subList(splitAt+1, inputs.size)

        val stacks = mutableListOf<MutableList<Char>>()
        for(i in 0 until numberOfStacks) {
            stacks.add(mutableListOf())
        }

        stackPositions.reversed().forEach {
            it.mapIndexed { index, c ->
                if(c != ' ') {
                    stacks[index].add(c)
                }
            }
        }

        moves.map {
            val parts = it.split(' ')
            Triple(parts[1].toInt(), parts[3].toInt()-1, parts[5].toInt()-1)
        }.forEach {
            for(i in 1..it.first) {
                stacks[it.third].add(stacks[it.second].last())
                stacks[it.second].removeAt(stacks[it.second].size-1)
            }
        }

        return String(stacks.map {
            it.last()
        }.toCharArray())
    }

    override fun solvePart2(inputs: List<String>): Any {
        val splitAt = inputs.indexOf("")
        val stackPositions = inputs.subList(0, splitAt-1).map {
            it.windowed(3, 4).map { c -> c[1] }
        }
        val numberOfStacks = stackPositions[stackPositions.size-1].size
        val moves = inputs.subList(splitAt+1, inputs.size)

        val stacks = mutableListOf<MutableList<Char>>()
        for(i in 0 until numberOfStacks) {
            stacks.add(mutableListOf())
        }

        stackPositions.reversed().forEach {
            it.mapIndexed { index, c ->
                if(c != ' ') {
                    stacks[index].add(c)
                }
            }
        }

        moves.map {
            val parts = it.split(' ')
            Triple(parts[1].toInt(), parts[3].toInt()-1, parts[5].toInt()-1)
        }.forEach {
            for(i in it.first downTo 1) {
                stacks[it.third].add(stacks[it.second][stacks[it.second].size-i])
            }
            for(i in it.first downTo 1) {
                stacks[it.second].removeAt(stacks[it.second].size-1)
            }
        }

        return String(stacks.map {
            it.last()
        }.toCharArray())
    }

}

fun main() {
    Day5().verifyAndSolve(
"""    [D]    
[N] [C]    
[Z] [M] [P]
 1   2   3 

move 1 from 2 to 1
move 3 from 1 to 3
move 2 from 2 to 1
move 1 from 1 to 2""", "CMZ", "MCD")
}