package homework5

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

fun zeroIntMatrix(height: Int, width: Int ): IntMatrix {
    val listMatrix = List(width) { List(height) { 0 } }

    return IntMatrix(listMatrix)
}

class IntMatrix(private val matrix: List<List<Int>>) {
    val height: Int
    val width: Int

    init {
        require(matrix.isMatrix()) {
            "matrix does not match the dimensions of the matrices, one line is shorter than the other"
        }

        height = matrix[0].size
        width = matrix.size
    }

    private fun List<List<Int>>.isMatrix(): Boolean {
        if (this.isNotEmpty()) {
            val firstLineSize = this.first().size

            this.find { it.size != firstLineSize } ?: return true
        }

        return false
    }

    operator fun times(other: IntMatrix): IntMatrix = runBlocking {
        require(width == other.height) {"not multiplied matrix, different dimension, $width != ${other.height}"}
        println(other.width)

        val resultsMatrix = mutableListOf<List<Deferred<Int>>>()

        for (columnIndex in 0 until other.width) {
            val results = mutableListOf<Deferred<Int>>()

            for (lineIndex in 0 until height) {
                val result = async {
                    multiplyLines(matrix.getLine(lineIndex), other.getColumn(columnIndex))
                }

                results.add(result)
            }

            resultsMatrix.add(results)
        }

        return@runBlocking await(resultsMatrix)
    }

    private suspend fun await(list: MutableList<List<Deferred<Int>>>): IntMatrix {
        val newListMatrix = MutableList(list.size) { MutableList(height) { 0 } }

        for (i in 0 until list.size) {
            for (j in 0 until height) {
                newListMatrix[i][j] = list[i][j].await()
            }
        }

        return IntMatrix(newListMatrix)
    }

    private fun multiplyLines(line: List<Int>, column: List<Int>): Int {
        require(line.size == column.size) {"not multiplied matrix, different dimension"} //TODO()

        var sum = 0
        for (i in line.indices) {
            println("line: ${line[i]}\ncolumn: ${column[i]} ")
            sum += line[i] * column[i]
            println("sum: $sum")
        }

        return sum
    }

    fun List<List<Int>>.getLine(lineIndex: Int): List<Int> {
        println("matrix $matrix")
        return matrix.map{ println(it[lineIndex])
            it[lineIndex]
        }
    }

    fun getColumn(index: Int): List<Int> {
        return matrix[index]
    }

    override fun toString(): String {
        return matrix.toString()
    }
}
