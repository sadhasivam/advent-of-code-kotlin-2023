package sj.aoc2023.puzzle

import sj.aoc2023.chassis.CalibrationDocument
import sj.aoc2023.chassis.calibrate
import sj.aoc2023.chassis.recalibrate


import readInput

const val TEST_PART1_CALIBRATION_VALUE = 142
const val PART1_CALIBRATION_VALUE = 54667
const val TEST_PART2_CALIBRATION_VALUE = 281
const val PART2_CALIBRATION_VALUE = 54203

data class Puzzle(
    val fileName: String,
    val isTestFile: Boolean,
    val exceptedValue: Int,
    val runner: (input: CalibrationDocument) -> Int
)

fun main() {
    fun part1(input: CalibrationDocument): Int {
        return input.calibrate()
    }

    fun part2(input: CalibrationDocument): Int {
        return input.recalibrate()
    }

    listOf(
        Puzzle("Day01/part1_test", true, TEST_PART1_CALIBRATION_VALUE, ::part1),
        Puzzle("Day01/part1", true, PART1_CALIBRATION_VALUE, ::part1),
        Puzzle("Day01/part2_test", true, TEST_PART2_CALIBRATION_VALUE, ::part2),
        Puzzle("Day01/part2", true, PART2_CALIBRATION_VALUE, ::part2)
    ).forEach {
        val calibrationDocument = readInput(it.fileName)
        val calibratedValue = it.runner(calibrationDocument)
        println("\n Puzzle ${it.fileName} Calibrated Value: $calibratedValue \n")
        if (it.isTestFile) {
            check(calibratedValue == it.exceptedValue)
        }

    }

}




