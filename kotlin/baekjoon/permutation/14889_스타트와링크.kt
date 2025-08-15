package permutation

import kotlin.math.abs

fun main() {
    val n = readln().toInt()
    val graph = List(n) {
        readln().split(" ").map { it.toInt() }
    }
    var ans = Int.MAX_VALUE

    fun makeCombinations(start: Int, count: Int, n: Int, r: Int, team: MutableList<Int>) {
        if (count == r) {

            val startTeam = calculateTeamAbility(team, graph)
            val linkTeam = calculateTeamAbility((0 until n).filter { it !in team }, graph)

            ans = minOf(abs(startTeam - linkTeam), ans)
            return
        }

        for (i in start until n) {
            team.add(i)
            makeCombinations(i + 1, count + 1, n, r, team)
            team.removeLast()
        }
    }
    makeCombinations(0, 0, n, n / 2, mutableListOf())

    println(ans)
}

private fun calculateTeamAbility(team: List<Int>, graph: List<List<Int>>): Int {
    var total = 0
    for (i in team.indices) {
        for (j in team.indices) {
            if (i == j) continue
            total += graph[team[i]][team[j]]
        }
    }
    return total
}