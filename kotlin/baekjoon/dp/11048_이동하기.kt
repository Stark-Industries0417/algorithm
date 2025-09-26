package dp

fun main() {
	val (n, m) = readln().split(" ").map { it.toInt() }
	val miro = Array(n) {
		readln().split(" ").map { it.toInt() }.toIntArray()
	}
	val dp = Array(n) {
		IntArray(m) { 0 }
	}
	val dx = intArrayOf(0, 1, 1)
	val dy = intArrayOf(1, 1, 0)

	println(dp.bfs(dx, dy, miro))
}

private fun Array<IntArray>.bfs(dx: IntArray, dy: IntArray, miro: Array<IntArray>): Int {
	val queue = ArrayDeque<Pair<Int, Int>>()
	queue.addLast(0 to 0)
	this[0][0] = miro[0][0]

	while (queue.isNotEmpty()) {
		val (x, y) = queue.removeFirst()

		for (i in 0 until 3) {
			val nx = x + dx[i]
			val ny = y + dy[i]

			if (nx < 0 || ny < 0 || nx >= this.size || ny >= this[nx].size) continue
			if (miro[nx][ny] + this[x][y] > this[nx][ny]) {
				this[nx][ny] = miro[nx][ny] + this[x][y]
			}
			queue.addLast(nx to ny)
		}
	}

	return this[size - 1][this[size - 1].size - 1]
}