
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

internal class MergeSortWithThreadsTest {
    @ParameterizedTest
    @MethodSource("testForEmptyList")
    fun `test for empty list`(classOfSort: MergeSortWithThreads<Int>, numberOfThreads: Int) {
        assertEquals(mutableListOf(), classOfSort.sort(numberOfThreads))
    }

    @ParameterizedTest
    @MethodSource("testForFilledInList")
    fun `test for filled in list`(classOfSort: MergeSortWithThreads<Int>, numberOfThreads: Int) {
        assertEquals(mutableListOf(-3, -1, 1, 4, 5), classOfSort.sort(numberOfThreads))
    }

    companion object {
        private val emptyListOfInt = MergeSortWithThreads(mutableListOf<Int>())
        private val filledInListOfInt = MergeSortWithThreads(mutableListOf(1, -3, 4, -1, 5))

        @JvmStatic
        fun testForEmptyList() = listOf(
            Arguments.of(emptyListOfInt, 0),
            Arguments.of(emptyListOfInt, 7),
            Arguments.of(emptyListOfInt, -1)
        )

        @JvmStatic
        fun testForFilledInList() = listOf(
            Arguments.of(filledInListOfInt, 0),
            Arguments.of(filledInListOfInt, 7),
            Arguments.of(filledInListOfInt, -1)
        )
    }
}
