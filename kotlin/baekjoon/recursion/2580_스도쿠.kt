package recursion

fun main() {
    val n = 9
    val sudoku = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    fun solve(idx: Int): Boolean {
        if (idx == 81) {
            sudoku.forEach {
                println(it.joinToString(" "))
            }
            return true
        }

        val row = idx / 9
        val col = idx % 9

        if (sudoku[row][col] != 0) return solve(idx + 1)

        for (num in 1..9) {
            if (sudoku.isValid(row, col, num)) {
                sudoku[row][col] = num
                if (solve(idx + 1)) return true
                sudoku[row][col] = 0
            }
        }
        return false
    }

    solve(0)
}

private fun Array<IntArray>.isValid(row: Int, col: Int, num: Int): Boolean {
    for (j in 0 until 9) {
        if (this[row][j] == num) return false
    }

    for (i in 0 until 9) {
        if (this[i][col] == num) return false
    }

    val boxRowStart = (row / 3) * 3
    val boxColStart = (col / 3) * 3
    for (i in boxRowStart until boxRowStart + 3) {
        for (j in boxColStart until boxColStart + 3) {
            if (this[i][j] == num) return false
        }
    }
    return true
}