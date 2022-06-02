import homework4.autoGenSortTestArguments
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class MergeSortWithCoroutinesTest {
    @ParameterizedTest
    @MethodSource("testForEmptyList")
    fun `test for empty list`(numberOfCoroutine: Int) {
        val sortClassOfInt = MergeSortWithCoroutines<Int>()
        assertEquals(mutableListOf<Int>(), sortClassOfInt.sort(mutableListOf(), numberOfCoroutine))
    }

    @ParameterizedTest
    @MethodSource("sortTestWithZeroCoroutine")
    fun `auto generated list coroutine sort test`(testList: MutableList<Int>, coroutineNumber: Int) {
        assertEquals(
            testList.sorted(),
            MergeSortWithCoroutines<Int>().sort(testList, coroutineNumber),
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
        fun sortTestWithZeroCoroutine() = autoGenSortTestArguments()
    }
}
