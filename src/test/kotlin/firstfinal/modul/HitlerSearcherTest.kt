package firstfinal.modul

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertFailsWith

internal class HitlerSearcherTest {
    @Test
    fun `incorrect dept in HitlerSearcher`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            HitlerSearcher(AVL_URL).search(-1, 5)
        }

        assertEquals(exception.message, "hitler search error, dept = -1, but must be grater than zero")
    }

    @Test
    fun `incorrect coroutine number in HitlerSearcher`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            HitlerSearcher(AVL_URL).search(6, 0)
        }

        assertEquals(exception.message, "hitler search error, coroutine number = 0, but must be grater than zero")
    }

    @ParameterizedTest
    @MethodSource("searchTest")
    fun `search test`(stepNumber: Int?, url: String, coroutineNumber: Int, deptNumber: Int) {
        assertEquals(stepNumber, HitlerSearcher(url).search(coroutineNumber, deptNumber))
    }

    companion object {
        private const val AVL_URL = "https://en.wikipedia.org/wiki/AVL_tree"
        private const val HITLER_URL = "https://en.wikipedia.org/wiki/Adolf_Hitler"

        @JvmStatic
        fun searchTest() = listOf(
            Arguments.of(1, HITLER_URL, 1, 1),
            Arguments.of(null, AVL_URL, 1, 2),
            Arguments.of(2, AVL_URL, 2, 3)
        )
    }
}
