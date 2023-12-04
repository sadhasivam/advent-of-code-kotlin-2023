package sj.aoc2023.puzzle

import readInput
import sj.aoc2023.chassis.getPartNumbers

const val TEST_GEAR_PARTNUMBERSUM = 4361
const val GEAR_PARTNUMBERSUM = 538046

fun main() {
    var input = readInput("Day03/part1_test") // Read each line as a String
    check(input.getPartNumbers().sum() == TEST_GEAR_PARTNUMBERSUM)

    input = readInput("Day03/part1") // Read each line as a String
    check(input.getPartNumbers().sum() == GEAR_PARTNUMBERSUM)
}