
fun main() {
    val firstMatrix = IntMatrix(listOf(listOf(1, 2, 3), listOf(-1, 2, -3), listOf(0, 2, 3)))
    val secondMatrix = IntMatrix(listOf(listOf(1, 2), listOf(2, 1), listOf(0, 0)))
    print(firstMatrix * secondMatrix)
}