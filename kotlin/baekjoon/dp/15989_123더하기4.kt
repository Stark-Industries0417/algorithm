package dp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() = with(BufferedReader(InputStreamReader(System.`in`))) {
	val bw = BufferedWriter(OutputStreamWriter(System.out))
	val n = readLine().toInt()

	val dp = IntArray(10_001) { 0 }
	dp[0] = 1
	dp[1] = 1

	for (i in 1..3) {
		for (j in 2..10_000) {
			if (i > j) continue
			dp[j] += dp[j - i]
		}
	}

	repeat(n) {
		val num = readLine().toInt()
		bw.write("${dp[num]}\n")
	}

	bw.flush()
	bw.close()
}