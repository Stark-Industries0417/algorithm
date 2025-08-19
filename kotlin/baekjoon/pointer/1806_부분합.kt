package pointer

fun main() {
    val (n, s) = readln().split(" ").map { it.toInt() }
    val arr = readln().split(" ").map { it.toInt() }

    var acc = 0; var left = 0
    var ans = Integer.MAX_VALUE

    for (right in (0 until n)) {
        acc += arr[right]

        while (acc >= s) {
            ans = minOf(ans, right - left + 1)
            acc -= arr[left]
            left++
        }
    }
    println(if (ans == Integer.MAX_VALUE) 0 else ans)
}