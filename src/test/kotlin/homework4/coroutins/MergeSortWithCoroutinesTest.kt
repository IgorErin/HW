import homework4.threads.MergeSortWithThreadsTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.random.Random

internal class MergeSortWithCoroutinesTest {
    @BeforeEach
    fun initLists() {
        smallList = MutableList(SMALL_ELEMENTS_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
        smallMediumList = MutableList(SMALL_MEDIUM_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
        bigMediumList = MutableList(BIG_MEDIUM_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
        bigList = MutableList(BIG_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
    }

    @ParameterizedTest
    @MethodSource("testForEmptyList")
    fun `test for empty list`(numberOfThreads: Int) {
        val sortClassOfInt = MergeSortWithCoroutines<Int>()
        assertEquals(mutableListOf<Int>(), sortClassOfInt.sort(mutableListOf(), numberOfThreads))
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
        private const val HIGH_BOUND_OF_RANDOM_NUMBERS = 1000000
        private const val SMALL_ELEMENTS_NUMBER = 1000
        private const val SMALL_MEDIUM_ELEMENT_NUMBER = 10000
        private const val BIG_MEDIUM_ELEMENT_NUMBER = 100000
        private const val BIG_ELEMENT_NUMBER = 1000000

        var smallList = MutableList(BIG_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
        var smallMediumList = MutableList(BIG_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
        var bigMediumList = MutableList(BIG_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }
        var bigList = MutableList(BIG_ELEMENT_NUMBER) { Random.nextInt(0, HIGH_BOUND_OF_RANDOM_NUMBERS) }

        @JvmStatic
        fun testForEmptyList() = listOf(
            Arguments.of(0),
            Arguments.of(7),
            Arguments.of(-1)
        )

        @JvmStatic
        fun sortTestWithZeroCoroutine() = listOf(
            Arguments.of(smallList, -1),
            Arguments.of(smallMediumList, -3),
            Arguments.of(bigMediumList, -5),
            Arguments.of(bigList, -100),
            Arguments.of(smallList, 0),
            Arguments.of(smallMediumList, 0),
            Arguments.of(bigMediumList, 0),
            Arguments.of(bigList, 0),
            Arguments.of(smallList, 1),
            Arguments.of(smallMediumList, 1),
            Arguments.of(bigMediumList, 1),
            Arguments.of(bigList, 1),
            Arguments.of(smallList, 5),
            Arguments.of(smallMediumList, 5),
            Arguments.of(bigMediumList, 5),
            Arguments.of(bigList, 5),
            Arguments.of(smallList, 10),
            Arguments.of(smallMediumList, 10),
            Arguments.of(bigMediumList, 10),
            Arguments.of(bigList, 10),
            Arguments.of(smallList, 20),
            Arguments.of(smallMediumList, 20),
            Arguments.of(bigMediumList, 20),
            Arguments.of(bigList, 20),
        )
    }
}
