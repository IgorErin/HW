package firstfinal.modul

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class HitlerSearcherTest {
    @ParameterizedTest
    @MethodSource("searchTest")
    fun `search test`(stepNumber: Int?, url: String, coroutineNumber: Int, deptNumber: Int) {
        assertEquals(stepNumber, HitlerSearcher(url).search(coroutineNumber, deptNumber))
    }

    companion object {
        @JvmStatic
        fun searchTest() = listOf(
            Arguments.of(1, "https://en.wikipedia.org/wiki/Adolf_Hitler", 1, 1),
            Arguments.of(null, "https://en.wikipedia.org/wiki/AVL_tree", 1, 2),
            Arguments.of(2, "https://en.wikipedia.org/wiki/AVL_tree", 2, 3)
        )
    }
}
