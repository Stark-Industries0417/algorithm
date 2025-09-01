package recursion

fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }.toIntArray()
    val operatorNums = readln().split(" ").map { it.toInt() }.toIntArray()

    val (max, min) = arr.calculateOperator(
        1,
        operatorNums[0],
        operatorNums[1],
        operatorNums[2],
        operatorNums[3],
        arr[0]
    )

    println(max)
    println(min)
}

private fun IntArray.calculateOperator(idx: Int, plus: Int, minus: Int, time: Int, divide: Int, currentNum: Int): Pair<Int, Int> {
    var max = Integer.MIN_VALUE; var min = Integer.MAX_VALUE
    if (idx == size) {
        max = maxOf(max, currentNum)
        min = minOf(min, currentNum)
        return Pair(max, min)
    }

    if (plus > 0) {
        val result = calculateOperator(idx + 1, plus - 1, minus, time, divide, currentNum + this[idx])
        max = maxOf(max, result.first)
        min = minOf(min, result.second)
    }
    if (minus > 0) {
        val result = calculateOperator(idx + 1, plus, minus - 1, time, divide, currentNum - this[idx])
        max = maxOf(max, result.first)
        min = minOf(min, result.second)
    }
    if (time > 0) {
        val result = calculateOperator(idx + 1, plus, minus, time - 1, divide, currentNum * this[idx])
        max = maxOf(max, result.first)
        min = minOf(min, result.second)
    }
    if (divide > 0) {
        val result = calculateOperator(idx + 1, plus, minus, time, divide - 1, currentNum / this[idx])
        max = maxOf(max, result.first)
        min = minOf(min, result.second)
    }

    return Pair(max, min)
}