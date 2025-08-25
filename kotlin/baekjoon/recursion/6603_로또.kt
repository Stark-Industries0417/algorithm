package recursion

fun main() {
    while (true) {
        val input = readln().split(" ").map { it.toInt() }
        val k = input[0]
        val s = input.subList(1, input.size)
        if (k == 0) break

        s.printLottoNumbers(0, k, 0, listOf())
        println()
    }
}

private fun List<Int>.printLottoNumbers(idx: Int, k: Int, start: Int, temp: List<Int>) {
    if (idx == 6) {
        println(temp.joinToString(" "))
        return
    }

    for (i in start until k) {
        printLottoNumbers(idx + 1, k, i + 1, temp + this[i])
    }
}