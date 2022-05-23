import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertFailsWith

internal class IntMatrixTest {
    @ParameterizedTest
    @MethodSource("correctProduct")
    fun `correct product`(correctMatrix: IntMatrix, leftMatrix: IntMatrix, rightMatrix: IntMatrix) {
        assertEquals(correctMatrix, leftMatrix * rightMatrix)
    }

    @ParameterizedTest
    @MethodSource("incorrectInit")
    fun `incorrect IntMatrix init`(list: List<List<Int>>) {
        val exception = assertFailsWith<IllegalArgumentException> { IntMatrix(list) }

        assertEquals(
            "matrix does not match the dimensions of the matrices, one line is shorter than the other or empty",
            exception.message
        )
    }

    @ParameterizedTest
    @MethodSource("incorrectProduct")
    fun `incorrect product test`(leftMatrix: IntMatrix, rightMatrix: IntMatrix) {
        val exception = assertFailsWith<IllegalArgumentException> { leftMatrix * rightMatrix }

        assertEquals("not multiplied matrix, different dimension", exception.message)
    }

    companion object {
        private val firstMatrix = IntMatrix(listOf(listOf(1, 2, 3), listOf(-1, 2, -3), listOf(0, 2, 3)))
        private val secondMatrix = IntMatrix(listOf(listOf(1, 2), listOf(2, 1), listOf(0, 0)))

        @JvmStatic
        fun correctProduct() = listOf(
            Arguments.of(IntMatrix(listOf(listOf(5, 4), listOf(3, 0), listOf(4, 2))), firstMatrix, secondMatrix),
            Arguments.of(firstMatrix, firstMatrix, idIntMatrix(3, 3)),
            Arguments.of(IntMatrix(List(3) { listOf(0) }), secondMatrix, zeroIntMatrix(2, 1))
        )

        @JvmStatic
        fun incorrectInit() = listOf(
            Arguments.of(listOf(listOf(1, 2, 3), listOf(1, 2, 3), listOf())),
            Arguments.of(listOf<List<Int>>()),
            Arguments.of(listOf(listOf<Int>(), listOf<Int>()))
        )

        @JvmStatic
        fun incorrectProduct() = listOf(
            Arguments.of(secondMatrix, firstMatrix, "", ""),
            Arguments.of(secondMatrix, idIntMatrix(1, 1)),
            Arguments.of(zeroIntMatrix(1, 2), idIntMatrix(1, 2))
        )
    }
}
