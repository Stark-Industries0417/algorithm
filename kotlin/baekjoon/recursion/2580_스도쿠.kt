package recursion

fun main() {
    val n = 9
    val sudoku = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    fun select(idx: Int): Boolean {
        if (idx == n * n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    print("${sudoku[i][j]} ")
                }
                println()
            }
            println()
            return true
        }

        if (sudoku[idx / n][idx % n] != 0)
            return select(idx + 1)
        else {
            for (choice in 1..n) {
                if (sudoku.rowOrColValid(idx, choice) && sudoku.check3x3(idx, choice)) {
                    sudoku[idx / n][idx % n] = choice
                    if (select(idx + 1)) return true
                    sudoku[idx / n][idx % n] = 0
                }
            }
        }
        return false
    }

    select(0)
}

private fun Array<IntArray>.rowOrColValid(idx: Int, i: Int): Boolean {
    val x = idx / 9; val y = idx % 9
    val rows = mutableSetOf<Int>()
    val cols = mutableSetOf<Int>()

    for (j in 0 until 9) {
        rows.add(this[x][j])
    }
    for (i in 0 until 9) {
        cols.add(this[i][y])
    }
    return i !in rows && i !in cols
}

private fun Array<IntArray>.check3x3(idx: Int, choice: Int): Boolean {
    val x = idx / 9; val y = idx % 9
    val startX = (x / 3) * 3; val startY = (y / 3) * 3
    val list = mutableListOf<Int>()
    for (i in startX until startX + 3) {
        for (j in startY until startY + 3) {
            list.add(this[i][j])
        }
    }
    return choice !in list
}