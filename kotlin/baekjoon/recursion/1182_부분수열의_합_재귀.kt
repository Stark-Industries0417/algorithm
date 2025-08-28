package recursion

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }

    arr.findSubSutSum(s)
        .let(::print)
}

private fun List<Int>.findSubSutSum(target: Int): Int {
    var count = 0

    fun subOfSubSet(idx: Int, currentSum: Int, selected: Int) {
        if (idx == size) {
            if (currentSum == target && selected > 0) count++
            return
        }

        subOfSubSet(idx + 1, currentSum + this[idx], selected + 1)
        subOfSubSet(idx + 1, currentSum, selected)
    }

    subOfSubSet(0, 0, 0)
    return count
}