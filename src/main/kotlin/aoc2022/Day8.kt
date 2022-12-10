package aoc2022

import aoc.LineBasedSolver

class Day8: LineBasedSolver(2022, 8) {

    override fun solvePart1(inputs: List<String>): Any {
        val forest = Forest()
        inputs.map { it.map { c -> c.digitToInt() } }
            .forEachIndexed { rowIndex, rowOfTrees ->
                rowOfTrees.forEachIndexed { columnIndex, treeHeight -> forest.addTree(rowIndex, columnIndex, treeHeight) } }

        return forest.countVisibleTrees()
    }

    override fun solvePart2(inputs: List<String>): Any {
        val forest = Forest()
        inputs.map { it.map { c -> c.digitToInt() } }
            .forEachIndexed { rowIndex, rowOfTrees ->
                rowOfTrees.forEachIndexed { columnIndex, treeHeight -> forest.addTree(rowIndex, columnIndex, treeHeight) } }

        return forest.getHighestScenicScore()
    }

    companion object {

        class Forest {

            private val trees = mutableMapOf<Pair<Int, Int>, Int>()
            private var maxRowIndex = 0
            private var maxColumnIndex = 0

            fun addTree(rowIndex: Int, columnIndex: Int, treeHeight: Int) {
                trees[Pair(rowIndex, columnIndex)] = treeHeight
                maxRowIndex = maxOf(maxRowIndex, rowIndex)
                maxColumnIndex = maxOf(maxColumnIndex, columnIndex)
            }

            fun countVisibleTrees(): Int {
                return trees.count {
                    isTreeVisible(it.key.first, it.key.second, it.value)
                }
            }

            private fun isTreeVisible(rowIndex: Int, columnIndex: Int, treeHeight: Int): Boolean {
                if(rowIndex == 0 || rowIndex == maxRowIndex || columnIndex == 0 || columnIndex == maxColumnIndex) {
                    return true
                }

                var visible = true
                for(i in 0 until rowIndex) {
                    if(trees[Pair(i, columnIndex)]!! >= treeHeight) {
                        visible = false
                    }
                }
                if(!visible) {
                    visible = true
                    for(i in rowIndex+1..maxRowIndex) {
                        if(trees[Pair(i, columnIndex)]!! >= treeHeight) {
                            visible = false
                        }
                    }
                }
                if(!visible) {
                    visible = true
                    for(i in 0 until columnIndex) {
                        if(trees[Pair(rowIndex, i)]!! >= treeHeight) {
                            visible = false
                        }
                    }
                }
                if(!visible) {
                    visible = true
                    for(i in columnIndex+1..maxColumnIndex) {
                        if(trees[Pair(rowIndex, i)]!! >= treeHeight) {
                            visible = false
                        }
                    }
                }

                return visible
            }

            fun getHighestScenicScore(): Int {
                return trees.map { getScenicScore(it.key.first, it.key.second, it.value) }.max()
            }

            private fun getScenicScore(rowIndex: Int, columnIndex: Int, treeHeight: Int): Int {
                if(rowIndex == 0 || rowIndex == maxRowIndex || columnIndex == 0 || columnIndex == maxColumnIndex) {
                    return 0
                }

                var leftScore = 0
                for(i in rowIndex-1 downTo 0) {
                    leftScore++
                    if(trees[Pair(i, columnIndex)]!! >= treeHeight) break
                }
                var rightScore = 0
                for(i in rowIndex+1..maxRowIndex) {
                    rightScore++
                    if(trees[Pair(i, columnIndex)]!! >= treeHeight) break
                }
                var topScore = 0
                for(i in columnIndex-1 downTo 0) {
                    topScore++
                    if(trees[Pair(rowIndex, i)]!! >= treeHeight) break
                }
                var bottomScore = 0
                for(i in columnIndex+1..maxColumnIndex) {
                    bottomScore++
                    if(trees[Pair(rowIndex, i)]!! >= treeHeight) break
                }

                return leftScore*rightScore*topScore*bottomScore
            }

        }

    }

}

fun main() {
    Day8().verifyAndSolve("""
        30373
        25512
        65332
        33549
        35390
    """.trimIndent(), 21, 8)
}