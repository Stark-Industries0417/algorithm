package dp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val (n, s, m) = br.readLine().split(" ").map { it.toInt() }.toIntArray()
	val volumes = br.readLine().split(" ").map { it.toInt() }.toIntArray()

	val dp = Array(n + 1) { BooleanArray(m + 1) { false } }
	dp[0][s] = true

	for (i in 0 until n) {
		for (j in 0..m) {
			if (dp[i][j]) {
				if (j + volumes[i] <= m) {
					dp[i + 1][j + volumes[i]] = true
				}
				if (j - volumes[i] >= 0) {
					dp[i + 1][j - volumes[i]] = true
				}
			}
		}
	}

	dp[n].lastIndexOf(true).let(::println)
}