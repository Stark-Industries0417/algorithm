package recursion

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }
    var count = 0

    fun findSubSetSum(start: Int, currentSum: Int) {
        if (start > 0 && currentSum == s) {
            count++
        }
        if (start == arr.size) return

        for (i in start until arr.size) {
            findSubSetSum(i + 1, currentSum + arr[i])
        }
    }

    findSubSetSum(0, 0)
    println(count)
}