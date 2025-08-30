package recursion

fun main() {
    val n = readln().toInt()
    val arr = readln().split(" ").map { it.toInt() }.toIntArray()
    val operatorNums = readln().split(" ").map { it.toInt() }.toIntArray()
    var max = Integer.MIN_VALUE; var min = Integer.MAX_VALUE

    fun calculate(idx: Int, plus: Int, minus: Int, time: Int, divide: Int, currentNum: Int) {
        if (idx == n) {
            max = maxOf(max, currentNum)
            min = minOf(min, currentNum)
            return
        }

        if (plus > 0) calculate(idx + 1, plus - 1, minus, time, divide, currentNum + arr[idx])
        if (minus > 0) calculate(idx + 1, plus, minus - 1, time, divide, currentNum - arr[idx])
        if (time > 0) calculate(idx + 1, plus, minus, time - 1, divide, currentNum * arr[idx])
        if (divide > 0) calculate(idx + 1, plus, minus, time, divide - 1, currentNum / arr[idx])
    }

    calculate(1, operatorNums[0], operatorNums[1], operatorNums[2], operatorNums[3], arr[0])

    println(max)
    print(min)
}