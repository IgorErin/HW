import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class ReshetoTest {

    @ParameterizedTest
    @MethodSource("testData")
    fun `test for expected input`(excepted: List<Int>, firstArg: Int) {
        assertEquals(excepted, createListOfPrime(firstArg))
    }

    @Test
    fun `test throw error for negative number`() {
        assertThrows<IllegalArgumentException> {
            createListOfPrime(-4)
        }
    }
    @Test
    fun `test throw error for small positive number`() {
        assertThrows<IllegalArgumentException> {
            createListOfPrime(1)
        }
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
