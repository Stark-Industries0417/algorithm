package permutation

import kotlin.math.abs

fun main() {
    val n = readln().toInt()
    val abilities = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    var minDiff = Int.MAX_VALUE

    fun combination(playerIdx: Int, selected: Int, team: IntArray) {
        if (selected == n / 2) {
            val otherTeam = (0 until n).filter { it !in team }.toIntArray()
            val teamA = team.calculateAbilities(abilities)
            val teamB = otherTeam.calculateAbilities(abilities)

            val abs = abs(teamA - teamB)
            minDiff = minOf(abs, minDiff)
            return
        }
        if (playerIdx >= n) return

        team[selected] = playerIdx
        combination(playerIdx + 1, selected + 1, team)
        combination(playerIdx + 1, selected, team)
    }
    combination(0, 0, IntArray(n / 2))

    print(minDiff)
}

private fun IntArray.calculateAbilities(abilities: Array<IntArray>) =
    sumOf { i ->
        sumOf { j ->
            if (i == j) return@sumOf 0
            abilities[i][j]
        }
    }
