import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.BeforeTest
import kotlin.test.assertFailsWith

internal class InteractionTest {
    var filledInStorageForTest = PerformedCommandStorage()
    var emptyStorageForTest = PerformedCommandStorage()

    @BeforeTest
    fun setup() {
        for (i in 1..5) {
            filledInStorageForTest.addToEnd(i)
        }
    }

    @Test
    fun `test throw error for first index out of range`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interaction(listOf("MOV", "7", "1"), filledInStorageForTest)
        }

        assertEquals("first index out of range", exception.message)
    }

    @Test
    fun `test throw error for second index out of range`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interaction(listOf("MOV", "1", "11"), filledInStorageForTest)
        }

        assertEquals("second index out of range", exception.message)
    }

    @Test
    fun `test throw error for empty list of actions`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interaction(listOf("REV"), emptyStorageForTest)
        }

        assertEquals("the stack of completed actions is empty", exception.message)
    }

    @Test
    fun `test throw error for invalid index`() {
        val exception = assertFailsWith<NumberFormatException> {
            interaction(listOf("MOV", "AsukaIsBetterThanRei", "2"), filledInStorageForTest)
        }

        assertEquals("For input string: \"AsukaIsBetterThanRei\"", exception.message)
    }

    @Test
    fun `test for invalid command`() {
        assertEquals("numbers: 1, 2, 3, 4, 5", interaction(listOf("MoV", "6e"), filledInStorageForTest))
    }

    @Test
    fun `test throw error for incomplete input for first argument`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interaction(listOf("MOV"), filledInStorageForTest)
        }

        assertEquals("incomplete input", exception.message)
    }

    @Test
    fun `test throw error for incomplete input for second argument`() {
        val exception = assertFailsWith<IllegalArgumentException> {
            interaction(listOf("MOV", "1"), filledInStorageForTest)
        }

        assertEquals("incomplete input", exception.message)
    }

    @Test
    fun `test for add to end`() {
        assertEquals("numbers: 1, 2, 3, 4, 5, 6", interaction(listOf("TTE", "6"), filledInStorageForTest))
    }

    @Test
    fun `test for add to begin`() {
        assertEquals("numbers: 0, 1, 2, 3, 4, 5", interaction(listOf("TTB", "0"), filledInStorageForTest))
    }

    @Test
    fun `test fot mov`() {
        assertEquals("numbers: 1, 3, 4, 2, 5", interaction(listOf("MOV", "1", "3"), filledInStorageForTest))
    }

    @Test
    fun `test fot revers action`() {
        assertEquals("numbers: 1, 2, 3, 4", interaction(listOf("REV"), filledInStorageForTest))
    }
}
