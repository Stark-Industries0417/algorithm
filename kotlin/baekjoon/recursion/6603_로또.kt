package recursion

fun main() {
    while (true) {
        val input = readln().split(" ").map { it.toInt() }
        val k = input[0]
        val s = input.subList(1, input.size)
        if (k == 0) break

        s.printLottoNumbers(0, k,BooleanArray(s.size), 0,mutableListOf())
        println()
    }
}

private fun List<Int>.printLottoNumbers(idx: Int, k: Int, check: BooleanArray, start: Int, temp: MutableList<Int>) {
    if (idx == 6) {
        println(temp.joinToString(" "))
        return
    }

    for (i in start until k) {
        if (check[i]) continue
        check[i] = true
        temp.add(this[i])
        printLottoNumbers(idx + 1, k, check, i + 1, temp)
        check[i] = false
        temp.removeLast()
    }
}