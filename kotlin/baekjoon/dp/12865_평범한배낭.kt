package dp

import java.io.BufferedReader
import java.io.InputStreamReader

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val (n, k) = br.readLine().split(" ").map { it.toInt() }

	val dp = Array(n + 1) { IntArray(k + 1) { 0 } }
	val things = Array(n + 1) { Pair(0, 0) }
	for (i in 1 .. n) {
		val (w, v) = br.readLine().split(" ").map { it.toInt() }
		things[i] = Pair(w, v)
	}

	for (i in 1 .. n) {
		for (w in 1 .. k) {
			if (things[i].first > w) {
				dp[i][w] = dp[i - 1][w]
			} else {
				dp[i][w] = maxOf(dp[i - 1][w], dp[i - 1][w - things[i].first] + things[i].second)
			}
		}
	}

	println(dp[n][k])
}
