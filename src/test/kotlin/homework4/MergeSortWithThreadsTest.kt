import homework4.threads.MergeSortWithThreads
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MergeSortWithThreadsTest {
    @ParameterizedTest
    @MethodSource("testForEmptyList")
    fun `test for empty list`(numberOfThreads: Int) {
        val sortClassOfInt = MergeSortWithThreads<Int>()
        assertEquals(mutableListOf<Int>(), sortClassOfInt.sort(mutableListOf(), numberOfThreads))
    }

    @ParameterizedTest
    @MethodSource("testForFilledInList")
    fun `test for filled in list`(numberOfThreads: Int) {
        val sortClassOfInt = MergeSortWithThreads<Int>()
        assertEquals(mutableListOf(-3, -1, 1, 4, 5), sortClassOfInt.sort(mutableListOf(1, -3, 4, -1, 5),
            numberOfThreads))
    }

    companion object {
        @JvmStatic
        fun testForEmptyList() = listOf(
            Arguments.of(0),
            Arguments.of(7),
            Arguments.of(-1)
        )

        @JvmStatic
        fun testForFilledInList() = listOf(
            Arguments.of(0),
            Arguments.of(7),
            Arguments.of(-1)
        )
    }
}
