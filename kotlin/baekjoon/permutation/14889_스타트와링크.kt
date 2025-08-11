package permutation

fun main() {
    val n = readln().toInt()
    val graph = List(n) {
        readln().split(" ").map { it.toInt() }
    }

    // 백 트래킹 조합으로 풀이 시도
}