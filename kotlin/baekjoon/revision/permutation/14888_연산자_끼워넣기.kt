package revision.permutation

fun main() {
    val n = readln().toInt()
    val nums = readln().split(" ").map { it.toInt() }.toIntArray()
    val ops = readln().split(" ").map { it.toInt() }.toIntArray()

    var maxValue: Int = Int.MIN_VALUE
    var minValue: Int = Int.MAX_VALUE

    fun go(idx: Int, num: Int, plus: Int, minus: Int, mult: Int, div: Int): Pair<Int, Int> {
        if (idx == n) {
            return Pair(maxOf(maxValue, num), minOf(minValue, num))
        }

        if (plus > 0) {
            val result = go(idx + 1, num + nums[idx], plus - 1, minus, mult, div)
            maxValue = maxOf(maxValue, result.first)
            minValue = minOf(minValue, result.second)
        }
        if (minus > 0) {
            val result = go(idx + 1, num - nums[idx], plus, minus - 1, mult, div)
            maxValue = maxOf(maxValue, result.first)
            minValue = minOf(minValue, result.second)
        }
        if (mult > 0) {
            val result = go(idx + 1, num * nums[idx], plus, minus, mult - 1, div)
            maxValue = maxOf(maxValue, result.first)
            minValue = minOf(minValue, result.second)
        }
        if (div > 0) {
            val result = go(idx + 1, num / nums[idx], plus, minus, mult, div - 1)
            maxValue = maxOf(maxValue, result.first)
            minValue = minOf(minValue, result.second)
        }

        return Pair(maxValue, minValue)
    }

    val (maxAns, minAns) = go(1, nums[0], ops[0], ops[1], ops[2], ops[3])

    println(maxAns)
    println(minAns)
}