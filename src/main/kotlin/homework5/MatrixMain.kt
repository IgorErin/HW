package homework5

fun main() {
    val mat = IntMatrix(listOf(listOf(1, 1), listOf(1, 2)))
    val mat2 = IntMatrix(listOf(listOf(1, 1), listOf(1, 2)))
    print(mat * mat2)
}