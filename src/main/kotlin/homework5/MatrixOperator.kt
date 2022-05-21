package homework5

import homework5.MatrixMultiplication.Companion.multiplyable

class MatrixMultiplication {

    companion object {
        fun multiply(leftMatrix: List<List<Int>>, rightMatrix: List<List<Int>>): List<List<Int>> {
            require(multiplyable(leftMatrix, rightMatrix))

            TODO()
        }

        fun multiplyable(leftMatrix: List<List<Int>>, rightMatrix: List<List<Int>>): Boolean {
            if (leftMatrix.isMatrix() && rightMatrix.isMatrix()) {
                return leftMatrix.size == rightMatrix[0].size
            }

            return false
        }

        fun List<List<Int>>.isMatrix(): Boolean {
            if (this.isNotEmpty()) {
                val firstLineSize = this[0].size

                this.find { it.size != firstLineSize } ?: return true
            }

            return false
        }
    }
}