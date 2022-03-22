import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertFailsWith

internal class InteractionsTest {
    var filledInStorageForTest = PerformedCommandStorage()
    var emptyStorageForTest = PerformedCommandStorage()

    @BeforeTest
    fun setup() {
        for (i in 1..5) {
            interactions(listOf("TTE", "$i"), filledInStorageForTest)
        }
    }

    @Test
    fun `test throw error for first index out of range`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interactions(listOf("MOV", "7", "1"), filledInStorageForTest)
        }

        assertEquals("first index out of range", exception.message)
    }

    @Test
    fun `test throw error for second index out of range`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interactions(listOf("MOV", "1", "11"), filledInStorageForTest)
        }

        assertEquals("second index out of range", exception.message)
    }

    @Test
    fun `test throw error for empty list of actions`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interactions(listOf("REV"), emptyStorageForTest)
        }

        assertEquals("the stack of completed actions is empty", exception.message)
    }

    @Test
    fun `test throw error for invalid index`() {
        val exception = assertFailsWith<NumberFormatException> {
            interactions(listOf("MOV", "AsukaIsBetterThanRei", "2"), filledInStorageForTest)
        }

        assertEquals("For input string: \"AsukaIsBetterThanRei\"", exception.message)
    }

    @Test
    fun `test for invalid command`() {
        assertEquals(listOf(1, 2, 3, 4, 5), interactions(listOf("MoV", "6ed"), filledInStorageForTest))
    }

    @Test
    fun `test throw error for incomplete input for first argument`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interactions(listOf("MOV"), filledInStorageForTest)
        }

        assertEquals("incomplete input", exception.message)
    }

    @Test
    fun `test throw error for incomplete input for second argument`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interactions(listOf("MOV", "1"), filledInStorageForTest)
        }

        assertEquals("incomplete input", exception.message)
    }

    @Test
    fun `test for add to end`() {
        assertEquals(listOf(1, 2, 3, 4, 5, 6), interactions(listOf("TTE", "6"), filledInStorageForTest))
    }

    @Test
    fun `test for add to begin`() {
        assertEquals(listOf(0, 1, 2, 3, 4, 5), interactions(listOf("TTB", "0"), filledInStorageForTest))
    }

    @Test
    fun `test fot mov`() {
        assertEquals(listOf(1, 3, 4, 2, 5), interactions(listOf("MOV", "1", "3"), filledInStorageForTest))
    }

    @Test
    fun `test fot revers action`() {
        assertEquals(listOf(1, 2, 3, 4), interactions(listOf("REV"), filledInStorageForTest))
    }
}
