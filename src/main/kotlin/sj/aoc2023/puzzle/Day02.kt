package sj.aoc2023.puzzle


import readInput
import sj.aoc2023.chassis.powerFewestCubesOfEachColor
import sj.aoc2023.chassis.parseGameRecord
import sj.aoc2023.chassis.sumPossibleGameIds
import sj.aoc2023.chassis.toColorCount

const val TEST_POSSIBLE_SUM_GAMEIDS = 8
const val  POSSIBLE_SUM_GAMEIDS = 2879
const val TEST_SUMPOWER_CUBES = 2286
const val  SUMPOWER_CUBES = 65122


fun main() {
   var input = readInput("Day02/part1_test")
   var games = input.map { parseGameRecord(it) }

   val red = "12 red".toColorCount()
   val green = "13 green".toColorCount()
   val blue = "14 blue".toColorCount()

   var result = sumPossibleGameIds(games, red, green, blue)
   check(result == TEST_POSSIBLE_SUM_GAMEIDS)

    input = readInput("Day02/part1")
    games = input.map { parseGameRecord(it) }

    result = sumPossibleGameIds(games, red, green, blue)
    check(result == POSSIBLE_SUM_GAMEIDS)
    input = readInput("Day02/part2_test")
    games = input.map { parseGameRecord(it) }
    var sumPower = games.sumOf {
        it.powerFewestCubesOfEachColor()
    }
    check(sumPower == TEST_SUMPOWER_CUBES)

    input = readInput("Day02/part1")
    games = input.map { parseGameRecord(it) }
    sumPower = games.sumOf {
        it.powerFewestCubesOfEachColor()
    }
    check(sumPower == SUMPOWER_CUBES)
}