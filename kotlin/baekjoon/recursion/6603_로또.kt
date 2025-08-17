package recursion

fun main() {
    var first = true

    while (true) {
        val input = readln().split(" ").map { it.toInt() }
        val k = input[0]

        if (k == 0) break

        if (!first) {
            println()
        }
        first = false

        val s = input.subList(1, input.size)
        s.printCombinations(0, 0, IntArray(6))
    }
}

private fun List<Int>.printCombinations(idx: Int, selected: Int, temp: IntArray) {
    if (selected == 6) {
        println(temp.joinToString(" "))
        return
    }

    if (idx >= this.size) return

    temp[selected] = this[idx]
    printCombinations(idx + 1, selected + 1, temp)
    printCombinations(idx + 1, selected, temp)
}