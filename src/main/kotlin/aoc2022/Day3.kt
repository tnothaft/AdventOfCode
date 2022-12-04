package aoc2022

import aoc.LineBasedSolver

class Day3: LineBasedSolver(2022, 3) {

    override fun solvePart1(inputs: List<String>): Any {
        return inputs.map {
            Pair(it.substring(0, it.length / 2), it.substring(it.length / 2))
        }.sumOf {
            var priority = -1
            for (c in 'a'..'z') {
                if (it.first.contains(c) && it.second.contains(c)) {
                    priority = (c - 'a' + 1)
                    break
                }
            }
            if (priority == -1) {
                for (c in 'A'..'Z') {
                    if (it.first.contains(c) && it.second.contains(c)) {
                        priority = (c - 'A' + 27)
                        break
                    }
                }
            }
            priority
        }
    }

    override fun solvePart2(inputs: List<String>): Any {
        return inputs.windowed(3, 3)
            .map { Triple(it[0], it[1], it[2]) }
            .sumOf {
                var badge = -1
                for (c in 'a'..'z') {
                    if (it.first.contains(c) && it.second.contains(c) && it.third.contains(c)) {
                        badge = (c - 'a' + 1)
                        break
                    }
                }
                if (badge == -1) {
                    for (c in 'A'..'Z') {
                        if (it.first.contains(c) && it.second.contains(c) && it.third.contains(c)) {
                            badge = (c - 'A' + 27)
                            break
                        }
                    }
                }
                badge
            }
    }

}

fun main() {
    Day3().verifyAndSolve("""
        vJrwpWtwJgWrhcsFMMfFFhFp
        jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL
        PmmdzqPrVvPwwTWBwg
        wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn
        ttgJtRGJQctTZtZT
        CrZsJsPPZsGzwwsLwLmpwMDw
    """.trimIndent(), 157, 70)
}