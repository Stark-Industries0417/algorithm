package dp

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.InputStreamReader
import java.io.OutputStreamWriter

fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val bw = BufferedWriter(OutputStreamWriter(System.out))

	val t = br.readLine().toInt()

	for (i in 0 until t) {
		val n = br.readLine().toInt()
		val files = br.readLine().split(" ").map { it.toInt() }.toIntArray()
		val dp = Array(n) { IntArray(n) { -1 } }

		dp.solution(0, n - 1, files)

		bw.write("${dp[0][n - 1]}\n")
	}

	bw.flush()
	bw.close()
}

private fun Array<IntArray>.solution(start: Int, end: Int, files: IntArray): Int {
	if (this[start][end] != -1) return this[start][end]
	if (start == end) return 0

	var ans = Int.MAX_VALUE
	val s = files.slice(start..end).sum()
	for (i in start until end) {
		val temp = solution(start, i, files) + solution(i + 1, end, files) + s
		if (ans> temp) ans = temp

		this[start][end] = ans
	}
	return ans
}