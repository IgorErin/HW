
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class IntMatrix(private val matrix: List<List<Int>>) {
    private val height: Int
    private val width: Int

    init {
        require(matrix.isMatrix()) {
            "matrix does not match the dimensions of the matrices, one line is shorter than the other or empty"
        }

        height = matrix.size
        width = matrix[0].size
    }

    private fun List<List<Int>>.getLine(lineIndex: Int): List<Int> {
        return this[lineIndex]
    }

    private fun getColumn(columnIndex: Int): List<Int> {
        return this.matrix.map{
            it[columnIndex]
        }
    }

    operator fun times(other: IntMatrix): IntMatrix = runBlocking {
        require(width == other.height) {"not multiplied matrix, different dimension" }

        val resultsMatrix = mutableListOf<List<Deferred<Int>>>()

        for (lineIndex in 0 until height) {
            val results = mutableListOf<Deferred<Int>>()

            for (columnIndex in 0 until other.width) {
                val result = async {
                    multiplyLines(matrix.getLine(lineIndex), other.getColumn(columnIndex))
                }

                results.add(result)
            }

            resultsMatrix.add(results)
        }

        return@runBlocking resultsMatrix.await()
    }

    private suspend fun MutableList<List<Deferred<Int>>>.await(): IntMatrix {
        val newMatrixList = mutableListOf<List<Int>>()

        for (lineIndex in 0 until this.size) {
            val sublist = mutableListOf<Int>()
            for (columnIndex in 0 until this[0].size) {
                sublist.add(this[lineIndex][columnIndex].await())
            }

            newMatrixList.add(sublist)
        }

        return IntMatrix(newMatrixList)
    }

    private fun multiplyLines(line: List<Int>, column: List<Int>): Int {
        require(line.size == column.size) { "not multiplied matrix, different dimension" }

        var sum = 0
        for (i in line.indices) {
            sum += line[i] * column[i]
        }

        return sum
    }

    private fun List<List<Int>>.isMatrix(): Boolean {
        if (this.isNotEmpty()) {
            val firstLineSize = this.first().size

            this.find { it.size != firstLineSize || it.isEmpty() } ?: return true
        }

        return false
    }

    override fun equals(other: Any?): Boolean = this.toString() == other.toString()

    override fun toString(): String {
        return matrix.toString()
    }
}
