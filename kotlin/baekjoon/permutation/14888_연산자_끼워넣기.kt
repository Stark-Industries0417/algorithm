package permutation


fun main() {
    val n = readln().toInt()
    val nums = readln().split(" ").map { it.toInt() }
    val operNums = readln().split(" ").map { it.toInt() }

    var maxValue = Int.MIN_VALUE
    var minValue = Int.MAX_VALUE

    fun backtrack(idx: Int, current: Int, plus: Int, minus: Int, mult: Int, dive: Int) {
        if (idx == n) {
            maxValue = maxOf(maxValue,current)
            minValue = minOf(minValue,current)
            return
        }

        if (plus > 0) backtrack(idx + 1, current + nums[idx], plus - 1, minus, mult, dive)
        if (minus > 0) backtrack(idx + 1, current - nums[idx], plus, minus - 1, mult, dive)
        if (mult > 0) backtrack(idx + 1, current * nums[idx], plus, minus, mult - 1, dive)
        if (dive > 0) backtrack(idx + 1, current / nums[idx], plus, minus, mult, dive - 1)
    }

    backtrack(1, nums[0], operNums[0], operNums[1], operNums[2], operNums[3])

    println(maxValue)
    println(minValue)
}