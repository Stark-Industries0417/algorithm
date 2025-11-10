package dp

import java.io.BufferedReader
import java.io.InputStreamReader
import java.util.LinkedList
import java.util.Queue


fun main() {
	val br = BufferedReader(InputStreamReader(System.`in`))
	val n = br.readLine().toInt()
	val scv = br.readLine().split(" ").map { it.toInt() }.toMutableList()
	val damages = arrayOf(
		intArrayOf(9, 3, 1),
		intArrayOf(9, 1, 3),
		intArrayOf(3, 9, 1),
		intArrayOf(3, 1, 9),
		intArrayOf(1, 9, 3),
		intArrayOf(1, 3, 9),
	)

	while (scv.size < 3)
		scv.add(0)

	val visited = Array(61) { Array(61) { IntArray(61) { -1 } } }
	val queue = LinkedList<IntArray>()
	visited[scv[0]][scv[1]][scv[2]] = 0
	queue.add(intArrayOf(scv[0], scv[1], scv[2]))

	while (queue.isNotEmpty()) {
		val cur = queue.poll()

		if (cur.all { it == 0 }) {
			println(visited[0][0][0])
			return
		}

		for (damage in damages) {
			val nx = maxOf(0, cur[0] - damage[0])
			val ny = maxOf(0, cur[1] - damage[1])
			val nz = maxOf(0, cur[2] - damage[2])

			if (visited[nx][ny][nz] != -1) continue
			visited[nx][ny][nz] = visited[cur[0]][cur[1]][cur[2]] + 1
			queue.offer(intArrayOf(nx, ny, nz))
		}
	}
}