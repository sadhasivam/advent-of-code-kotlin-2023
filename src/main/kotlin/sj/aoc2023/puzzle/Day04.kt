package sj.aoc2023.puzzle

import readInput
import sj.aoc2023.chassis.totalCards
import sj.aoc2023.chassis.winningTotal

const val TEST_SCRATCHCARD_POINTS = 13
const val SCRATCHCARD_POINTS = 25010

const val TEST_SCRATCHCARD_SCORE = 30
const val SCRATCHCARD_SCORE = 9924412

fun main() {
    check(readInput("Day04/part1_test").winningTotal() == TEST_SCRATCHCARD_POINTS)
    println(" Day04 -> Part1 Test passed. ✅ ")
    check(readInput("Day04/part1").winningTotal() == SCRATCHCARD_POINTS)
    println(" Day04 -> Part1 passed. ✅")

    check(readInput("Day04/part1_test").totalCards() == TEST_SCRATCHCARD_SCORE)
    println(" Day04 -> Part2 Test passed. ✅ ")

    check(readInput("Day04/part1").totalCards() == SCRATCHCARD_SCORE)
    println(" Day04 -> Part2 Test passed. ✅ ")
}