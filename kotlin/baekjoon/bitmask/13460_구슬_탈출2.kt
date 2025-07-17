package baekjoon.bitmask

import java.util.LinkedList
import kotlin.math.abs


fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val board = Array(n) {
        readln().toCharArray()
    }

    var redX = 0; var redY = 0
    var blueX = 0; var blueY = 0

    for (i in board.indices) {
        for (j in board[i].indices) {
            when (board[i][j]) {
                'R' -> { redX = i; redY = j; board[i][j] = '.' }
                'B' -> { blueX = i; blueY = j; board[i][j] = '.' }
            }
        }
    }

    val dx = intArrayOf(0, 0, -1, 1)
    val dy = intArrayOf(1, -1, 0, 0)

    val queue = LinkedList<State>()
    val visited = mutableSetOf<String>()
    val state = State(redX, redY, blueX, blueY, 0)
    queue.offer(state)
    visited.add("$redX,$redY,$blueX,$blueY")

    while (queue.isNotEmpty()) {
        val current = queue.poll()

        for (dir in 0 until 4) {
            var nrx = current.redX
            var nry = current.redY
            var nbx = current.blueX
            var nby = current.blueY

            while (board[nrx + dx[dir]][nrx + dy[dir]] != '#') {
                nrx += dx[dir]; nry += dy[dir]
                if (board[nrx][nry] == 'o') break
            }
            while (board[nbx + dx[dir]][nby + dy[dir]] != '#') {
                nbx += dx[dir]; nby += dy[dir]
                if (board[nbx][nby] == 'o') break
            }

            val redDist = abs(nrx - current.redX + nry - current.redY)
            val blueDist = abs(nbx - current.blueX + nby - current.blueY)

            if (redDist > blueDist) {
                nrx -= dx[dir]; nry -= dy[dir]
            } else {
                nbx -= dx[dir]; nby -= dy[dir]
            }

            if (board[nbx][nby] == 'o') continue
            if (board[nrx][nry] == 'o') {
                println(current.count + 1)
                return
            }

            queue.offer(State(nrx, nry, nbx, nby, current.count + 1))
        }
    }

}

data class State(
    val redX: Int,
    val redY: Int,
    val blueX: Int,
    val blueY: Int,
    val count: Int
)