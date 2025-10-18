package dp

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (n, k) = br.readLine().split(" ").map { it.toInt() }

	val dp = Array(n) { IntArray(n) { 0 } }
	val things = mutableListOf<Pair<Int, Int>>()
	repeat(n) {
		val (a, b) = br.readLine().split(" ").map { it.toInt() }
		things.add(Pair(a, b))
	}

	for (i in 0 until n) {
		dp[i][i] = things[i].second
	}

	fun solution(start: Int, end: Int): Int {
		if (dp[start][end] != 0) return dp[start][end]
		if (dp[start][end] == -1) return 0

		if (start == end) return things[start].let {
			dp[start][end] = if (it.first > k) -1 else it.second
			dp[start][end]
		}

		var ans = Int.MIN_VALUE
		for (i in start until end) {
			val temp = solution(start, i) + solution(i + 1, end)
			val weight = things.slice(start..end).sumOf { it.first }
			if (weight > k) continue
			if (temp > ans) ans = temp
		}
		dp[start][end] = ans
		return ans
	}

	solution(0, n - 1)

	println(dp.maxOf { it.max() })
}
