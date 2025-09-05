package recursion

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val graph = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    val dx = intArrayOf(0, 0, 1, -1)
    val dy = intArrayOf(1, -1, 0, 0)
    var ans = Integer.MIN_VALUE

    fun dfs(x: Int, y: Int, choice: Int, currentSum: Int, visited: Array<BooleanArray>) {
        if (choice == 4) {
            ans = maxOf(ans, currentSum)
            return
        }

        for (i in 0 until 4) {
            val nx = x + dx[i]
            val ny = y + dy[i]

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || visited[nx][ny]) continue

            visited[nx][ny] = true
            dfs(nx, ny, choice + 1, currentSum + graph[nx][ny], visited)
            visited[nx][ny] = false

            if (choice == 2) {
                visited[nx][ny] = true
                dfs(x, y, choice + 1, currentSum + graph[nx][ny], visited)
                visited[nx][ny] = false
            }
        }
    }

    val visited = Array(n) { BooleanArray(m) { false } }
    for (i in 0 until n) {
        for (j in 0 until m) {
            visited[i][j] = true
            dfs(i, j, 1, graph[i][j], visited)
            visited[i][j] = false
        }
    }

    println(ans)
}