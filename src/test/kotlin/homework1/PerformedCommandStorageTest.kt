
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class PerformedCommandStorageTest {
    var storageForTest = PerformedCommandStorage()
    var storageForTestInit = PerformedCommandStorage()

    init {
        for (i in 1..5) {
            storageForTestInit.addToEnd(i)
        }
    }

    @Test
    fun `test throw error for first index out of range`() {
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { interaction(listOf("MOV", "7", "1"), storageForTestInit) }
        )
        assertEquals("first index out of range", exception.message)
    }

    @Test
    fun `test throw error for second index out of range`() {
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { interaction(listOf("MOV", "1", "11"), storageForTestInit) }
        )
        assertEquals("second index out of range", exception.message)
    }

    @Test
    fun `test throw error for empty list of actions`() {
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { interaction(listOf("REV"), storageForTest) }
        )
        assertEquals("the stack of completed actions is empty", exception.message)
    }

    @Test
    fun `test throw error for invalid index`() {
        val exception = assertFailsWith<NumberFormatException>(
            block = { interaction(listOf("MOV", "AsukaIsBetterThanRei", "2"), storageForTestInit) }
        )
        assertEquals("For input string: \"AsukaIsBetterThanRei\"", exception.message)
    }

    @Test
    fun `test for invalid command`() {

        assertEquals(listOf(1, 2, 3, 4, 5), interaction(listOf("MoV", "6e"), storageForTestInit))
    }

    @Test
    fun `test throw error for incomplete input for first argument`() {
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { interaction(listOf("MOV"), storageForTestInit) }
        )
        assertEquals("incomplete input", exception.message)
    }

    @Test
    fun `test throw error for incomplete input for second argument`() {
        val exception = assertFailsWith<IllegalArgumentException>(
            block = { interaction(listOf("MOV", "1"), storageForTest) }
        )
        assertEquals("incomplete input", exception.message)
    }

    @Test
    fun `test for add to end`() {
        assertEquals(listOf(1, 2, 3, 4, 5, 6), interaction(listOf("TTE", "6"), storageForTestInit))
    }

    @Test
    fun `test for add to begin`() {
        assertEquals(listOf(0, 1, 2, 3, 4, 5), interaction(listOf("TTB", "0"), storageForTestInit))
    }
    @Test
    fun `test fot mov`() {
        assertEquals(listOf(1, 3, 4, 2, 5), interaction(listOf("MOV", "1", "3"), storageForTestInit))
    }
    @Test
    fun `test fot revers action`() {
        assertEquals(listOf(1, 2, 3, 4), interaction(listOf("REV"), storageForTestInit))
    }
}
