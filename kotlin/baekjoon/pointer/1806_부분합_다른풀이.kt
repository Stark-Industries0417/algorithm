package pointer

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }

    var acc = arr[0]
    var left = 0
    var right = 0
    var ans = Integer.MAX_VALUE

    while (left <= right && right < n) {
        if (acc >= s) {
            ans = minOf(ans, right - left + 1)
            acc -= arr[left++]
        } else {
            right++
            if (right < n) acc += arr[right]
        }
    }
    println(if (ans == Integer.MAX_VALUE) 0 else ans)
}