package homework4.threads

import MergeSortWithThreads
import homework4.autoGenSortTestArguments
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import pow

internal class MergeSortWithThreadsTest {
    @ParameterizedTest
    @MethodSource("testForEmptyList")
    fun `test for empty list`(numberOfThreads: Int) {
        val sortClassOfInt = MergeSortWithThreads<Int>()
        assertEquals(mutableListOf<Int>(), sortClassOfInt.sort(mutableListOf(), numberOfThreads))
    }

    @ParameterizedTest
    @MethodSource("sortTestWithZeroThreads")
    fun `auto generated list threads sort test`(testList: MutableList<Int>, coroutineNumber: Int) {
        assertEquals(
            testList.sorted(),
            MergeSortWithThreads<Int>().sort(testList, coroutineNumber),
            "Coroutine sort test failed for ${2.pow(coroutineNumber)} coroutine number, ${testList.size} elements"
        )
    }

    companion object {
        @JvmStatic
        fun testForEmptyList() = listOf(
            Arguments.of(0),
            Arguments.of(7),
            Arguments.of(-1)
        )

        @JvmStatic
        fun sortTestWithZeroThreads() = autoGenSortTestArguments()
    }
}
