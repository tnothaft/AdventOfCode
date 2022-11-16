package aoc2021

import aoc.LineBasedSolver

class Day4: LineBasedSolver(2021, 4) {

    override fun solvePart1(inputs: List<String>): Int {
        val numbers = inputs.extractNumbers()
        val boards = inputs.extractBoards()
        for(number in numbers) {
            boards.forEach {
                if(it.markNumber(number)) {
                    return it.sum() * number
                }
            }
        }
        return 0
    }

    override fun solvePart2(inputs: List<String>): Int {
        val numbers = inputs.extractNumbers()
        var boards = inputs.extractBoards()
        for(number in numbers) {
            if(boards.size == 1) {
                if(boards.first().markNumber(number)) {
                    return boards.first().sum() * number
                }
            } else {
                boards = boards.filter { !it.markNumber(number) }
            }
        }

        return 0
    }

    companion object {

        fun List<String>.extractNumbers() =
            this[0].split(",").map { it.toInt() }

        fun List<String>.extractBoards() =
            this.subList(2, size).windowed(5, 6)
                .map { board -> board.map { row -> row.trim().split(" +".toRegex(), 5)
                    .map { field -> field.toInt() }.toMutableList() }.toMutableList() }

        fun MutableList<MutableList<Int>>.markNumber(number: Int): Boolean {
            for(i in 0..4) {
                for(j in 0 ..4) {
                    if(this[i][j] == number) {
                        this[i][j] = -1
                        return this.check(i, j)
                    }
                }
            }
            return false
        }

        private fun MutableList<MutableList<Int>>.check(x: Int, y: Int): Boolean {
            if(this[x].fold(true) { acc, i -> acc && i  == -1 })
                return true
            if(this.fold(true) { acc, i -> acc && i[y] == -1 })
                return true
            return false
        }

        fun MutableList<MutableList<Int>>.sum(): Int {
            var sum = 0
            for(i in 0..4) {
                for(j in 0..4) {
                    if(this[i][j] >= 0)
                        sum += this[i][j]
                }
            }
            return sum
        }

    }

}

fun main() {
    Day4().verifyAndSolve("""
        7,4,9,5,11,17,23,2,0,14,21,24,10,16,13,6,15,25,12,22,18,20,8,19,3,26,1
        
        22 13 17 11  0
         8  2 23  4 24
        21  9 14 16  7
         6 10  3 18  5
         1 12 20 15 19
        
         3 15  0  2 22
         9 18 13 17  5
        19  8  7 25 23
        20 11 10 24  4
        14 21 16 12  6
        
        14 21 17 24  4
        10 16 15  9 19
        18  8 23 26 20
        22 11 13  6  5
         2  0 12  3  7
    """.trimIndent(), 4512, 1924)
}