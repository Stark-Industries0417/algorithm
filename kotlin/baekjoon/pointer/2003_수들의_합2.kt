package pointer

fun main() {
    val (n, m) = readln().split(" ").map { it.toInt() }
    val a = readln().split(" ").map { it.toInt() }

    var left = 0; var right = 0
    var s = 0; var count = 0

    for (right in 0 until n) {
        s += a[right]

        while (left <= right && s > m) {
            s -= a[left]
            left++
        }

        if (s == m) count++
    }
    println(count)
}