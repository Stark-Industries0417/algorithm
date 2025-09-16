package recursion

fun main() {
    val n = 9
    val sudoku = Array(n) {
        readln().split(" ").map { it.toInt() }.toIntArray()
    }

    fun select(idx: Int) {
        if (idx == n * n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    print("${sudoku[i][j]} ")
                }
                println()
            }
            println()
            return
        }

        if (sudoku[idx / n][idx % n] != 0)
            select(idx + 1)
        else {
            for (i in 1..n) {
                if (i !in sudoku[idx / n] && sudoku.colFilter(idx, i) && i !in sudoku.check3x3(idx)) {
                    sudoku[idx / n][idx % n] = i
                    select(idx + 1)
                    sudoku[idx / n][idx % n] = 0
                }
            }
        }
    }
}

private fun Array<IntArray>.colFilter(idx: Int, i: Int): Boolean {
    val x = idx / 9; val y = idx % 9
    val set = mutableSetOf<Int>()
    for (j in 0 until 9) {
        set.add(this[x][j])
    }
    return i in set
}

private fun Array<IntArray>.check3x3(idx: Int): List<Int> {
    val x = idx / 3; val y = idx % 3

}