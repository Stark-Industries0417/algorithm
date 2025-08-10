package permutation

fun main() {
    val n = readln().toInt()
    val nums = readln().split(" ").map { it.toInt() }
    val operNums = readln().split(" ").map { it.toInt() }
    val operators = listOf('+', '-', '*', '/')
    val operatorsList = mutableListOf<Char>()
    List(4) {
        repeat(operNums[it]) { _ ->
            operatorsList.add(operators[it])
        }
    }



}

fun calc(operators: List<Char>, nums: MutableList<Int>): Int {
    for (i in 1 until nums.size) {
        when (operators[i - 1]) {
            '+' -> nums[i] = nums[i - 1] + nums[i]
            '-' -> nums[i] = nums[i - 1] - nums[i]
            '*' -> nums[i] = nums[i - 1] * nums[i]
            '/' -> nums[i] = nums[i - 1] / nums[i]
        }
    }
    return nums[nums.size - 1]
}