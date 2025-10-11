package dp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	val t = br.readLine().toInt()

	repeat(t) {
		val n = br.readLine().toInt()
		val files = br.readLine().split(" ").map { it.toInt() }.toIntArray()

		val sum = IntArray(n)
		sum[0] = files[0]
		for (i in 1 until n) {
			sum[i] = sum[i - 1] + files[i]
		}

		val dp = Array(n) { IntArray(n) { Int.MAX_VALUE } }
		for (i in 0 until n) {
			dp[i][i] = 0
		}

		for (len in 2 .. n) {
			for (i in 0 .. n - len) {
				val j = i + len - 1
				for (k in i until j) {
					val cost = sum[j] - (if (i > 0) sum[i - 1] else 0)
					dp[i][j] = minOf(dp[i][j], dp[i][k] + dp[k + 1][j] + cost)
				}
			}
		}
		bw.write("${dp[0][n - 1]}\n")
	}

	bw.flush()
	bw.close()
}