package aoc2015

import aoc.readInputs

fun part1(): Int {
    val instructions = readInputs(2015, 1)
    return instructions.count { it == '(' } - instructions.count { it == ')' }
}

fun part2(): Int {
    val instructions = readInputs(2015, 1)
    var floor = 0
    var instruction = 1
    instructions.forEach {
        when(it) {
            '(' -> floor++
            ')' -> floor--
        }
        if(floor == -1)
            return instruction
        instruction++
    }
    return 0
}

fun main() {
    println("Part 1: ${part1()}")
    println("Part 2: ${part2()}")
}