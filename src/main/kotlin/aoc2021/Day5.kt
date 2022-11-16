package aoc2021

import aoc.LineBasedSolver

class Day5: LineBasedSolver(2021, 5) {

    override fun solvePart1(inputs: List<String>): Int {
        val counter = HashMap<Pair<Int, Int>, Int>()
        inputs.map {
            val coords = it.split(" -> ")
            val coord1 = coords[0].split(",").map { c -> c.toInt() }
            val coord2 = coords[1].split(",").map { c -> c.toInt() }
            Pair(Pair(coord1[0], coord1[1]), Pair(coord2[0], coord2[1]))
        }.map {
            val points = ArrayList<Pair<Int, Int>>()
            if(it.first.first == it.second.first) {
                val  fromTo = when(it.first.second <= it.second.second) {
                    true -> Pair(it.first.second, it.second.second)
                    false -> Pair(it.second.second, it.first.second)
                }
                for(i in fromTo.first..fromTo.second) {
                    points.add(Pair(it.first.first, i))
                }
            } else if(it.first.second == it.second.second) {
                val  fromTo = when(it.first.first <= it.second.first) {
                    true -> Pair(it.first.first, it.second.first)
                    false -> Pair(it.second.first, it.first.first)
                }
                for(i in fromTo.first..fromTo.second) {
                    points.add(Pair(i, it.first.second))
                }
            }
            points
        }.forEach {
            list -> list.forEach {
                val count = (counter[it]?.or(0) ?: 0) + 1
                counter[it] = count
            }
        }
        return counter.filterValues { it > 1 }.count()
    }

    override fun solvePart2(inputs: List<String>): Int {
        val counter = HashMap<Pair<Int, Int>, Int>()
        inputs.map {
            val coords = it.split(" -> ")
            val coord1 = coords[0].split(",").map { c -> c.toInt() }
            val coord2 = coords[1].split(",").map { c -> c.toInt() }
            Pair(Pair(coord1[0], coord1[1]), Pair(coord2[0], coord2[1]))
        }.map {
            val points = ArrayList<Pair<Int, Int>>()
            if(it.first.first == it.second.first) {
                val  fromTo = when(it.first.second <= it.second.second) {
                    true -> Pair(it.first.second, it.second.second)
                    false -> Pair(it.second.second, it.first.second)
                }
                for(i in fromTo.first..fromTo.second) {
                    points.add(Pair(it.first.first, i))
                }
            } else if(it.first.second == it.second.second) {
                val  fromTo = when(it.first.first <= it.second.first) {
                    true -> Pair(it.first.first, it.second.first)
                    false -> Pair(it.second.first, it.first.first)
                }
                for(i in fromTo.first..fromTo.second) {
                    points.add(Pair(i, it.first.second))
                }
            } else {
                if(it.first.first <= it.second.first) {
                    val step = when(it.first.second < it.second.second) {
                        true -> 1
                        false -> -1
                    }
                    for(i in it.first.first..it.second.first) {
                        points.add(Pair(i, it.first.second + (i - it.first.first) * step))
                    }
                } else {
                    val step = when(it.second.second < it.first.second) {
                        true -> 1
                        false -> -1
                    }
                    for(i in it.second.first..it.first.first) {
                        points.add(Pair(i, it.second.second + (i - it.second.first) * step))
                    }
                }
            }
            points
        }.forEach {
            list -> list.forEach {
                val count = (counter[it]?.or(0) ?: 0) + 1
                counter[it] = count
            }
        }
        return counter.filterValues { it > 1 }.count()
    }

}

fun main() {
    Day5().verifyAndSolve("""
        0,9 -> 5,9
        8,0 -> 0,8
        9,4 -> 3,4
        2,2 -> 2,1
        7,0 -> 7,4
        6,4 -> 2,0
        0,9 -> 2,9
        3,4 -> 1,4
        0,0 -> 8,8
        5,5 -> 8,2
    """.trimIndent(), 5, 12)
}