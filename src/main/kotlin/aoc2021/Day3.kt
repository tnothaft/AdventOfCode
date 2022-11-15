package aoc2021

import aoc.LineBasedSolver

class Day3: LineBasedSolver(2021, 3) {

    override fun solvePart1(inputs: List<String>): Int {
        val rate = inputs.fold(Rate(inputs[0].length)) { rate: Rate, s: String -> rate.add(s) }
        val gammaRate = rate.getGamma()
        val epsilonRate = rate.getEpsilon()
        return gammaRate * epsilonRate
    }

    override fun solvePart2(inputs: List<String>): Int {
        val length = inputs[0].length
        //val rate = inputs.fold(Rate(length)) { rate: Rate, s: String -> rate.add(s) }

        var oxygen = inputs
        for(i in 0 until length) {
            val rate = oxygen.fold(Rate(length)) { rate: Rate, s: String -> rate.add(s) }
            val oxygenBit = rate.getOxygenBit(i)
            oxygen = oxygen.filter { it[i] == oxygenBit }
            if(oxygen.size == 1)
                break
        }
        if(oxygen.size != 1)
            error("Should be only one oxygen number")

        var co2 = inputs
        for(i in 0 until length) {
            val rate = co2.fold(Rate(length)) { rate: Rate, s: String -> rate.add(s) }
            val co2Bit = rate.getCO2Bit(i)
            co2 = co2.filter { it[i] == co2Bit }
            if(co2.size == 1)
                break
        }
        if(oxygen.size != 1)
            error("Should be only one CO2 number")

        return oxygen[0].toBinaryNumber() * co2[0].toBinaryNumber()
    }

    class Rate(private val length: Int) {

        private val bitCounter = IntArray(length)
        private var measurements = 0

        fun add(number: String): Rate {
            number.forEachIndexed { index, c -> bitCounter[index] += c.digitToInt() }
            measurements++
            return this
        }

        fun getGamma(): Int {
            var gammaRate = 0
            for(i in 0 until length) {
                gammaRate *= 2
                if(bitCounter[i] > measurements/2.0) {
                    gammaRate++
                }
            }
            return gammaRate
        }

        fun getEpsilon(): Int {
            var epsilonRate = 0
            for(i in 0 until length) {
                epsilonRate *= 2
                if(bitCounter[i] < measurements/2.0) {
                    epsilonRate++
                }
            }
            return epsilonRate
        }

        fun getOxygenBit(index: Int) =
            when(bitCounter[index] >= measurements/2.0) {
                true -> '1'
                false -> '0'
            }

        fun getCO2Bit(index: Int) =
            when(bitCounter[index] >= measurements/2.0) {
                true -> '0'
                false -> '1'
            }

    }

    companion object {

        fun String.toBinaryNumber(): Int {
            var number = 0
            for(element in this) {
                number = 2*number + element.digitToInt()
            }
            return number
        }

    }

}

fun main() {
    Day3().verifyAndSolve("""
        00100
        11110
        10110
        10111
        10101
        01111
        00111
        11100
        10000
        11001
        00010
        01010
    """.trimIndent(), 198, 230)
}