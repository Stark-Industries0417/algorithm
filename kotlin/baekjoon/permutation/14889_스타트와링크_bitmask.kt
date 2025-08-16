package permutation

import kotlin.math.abs

fun main() {
    val n = readln().toInt()
    val abilities = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }
    var minDiff = Int.MAX_VALUE

    for (mask in 1 until (1 shl n)) {
        if (mask.countOneBits() != n / 2) continue
        val teamA = (0 until n).filter { (mask and (1 shl it)) != 0 }
        val teamB = (0 until n).filter { (mask and (1 shl it)) == 0 }

        val abilityA = teamA.calculateTeamAbility(abilities)
        val abilityB = teamB.calculateTeamAbility(abilities)

        minDiff = minOf(minDiff, abs(abilityA - abilityB))
    }

    print(minDiff)
}


private fun List<Int>.calculateTeamAbility(abilities: Array<IntArray>): Int =
    sumOf { i ->
        sumOf { j ->
            if (i == j) return@sumOf 0
            abilities[i][j]
        }
    }