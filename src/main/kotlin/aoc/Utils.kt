package aoc

import java.io.File

fun readInputs(year: Int, day: Int): String =
    File("data/${year}/${day}_inputs.txt").readText()

fun readInputLines(year: Int, day: Int): List<String> =
    readInputs(year, day).lines()