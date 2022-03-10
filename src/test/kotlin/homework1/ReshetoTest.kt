import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertFailsWith

internal class ReshetoTest {

    @ParameterizedTest
    @MethodSource("testData")
    fun `test for expected input`(excepted: List<Int>, firstArg: Int) {
        assertEquals(excepted, createListOfPrime(firstArg))
    }

    @Test
    fun `given incorrect input`() {
        val exception = assertFailsWith<IllegalArgumentException> { createListOfPrime(-2) }
        assertEquals("Count must be >= 2, was -2", exception.message)
    }

    companion object {
        @JvmStatic
        fun testData() = listOf(
            Arguments.of(listOf(2, 3, 5, 7, 11), 11),
            Arguments.of(listOf(2, 3, 5, 7, 11, 13), 15),
            Arguments.of(listOf(2), 2)
        )
    }
}
