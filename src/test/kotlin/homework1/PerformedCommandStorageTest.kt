
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class PerformedCommandStorageTest {
    var storageForTest = PerformedCommandStorage()

    @Test
    fun `test throw error for index out of range`() {
        assertThrows<IllegalArgumentException> {
            interaction(listOf("MOV", "1", "11"), storageForTest)
        }
    }

    @Test
    fun `test throw error for empty list of actions`() {
        assertThrows<IllegalArgumentException> {
            interaction(listOf("UND"), storageForTest)
        }
    }

    @Test
    fun `test throw error for invalid index`() {
        assertThrows<NumberFormatException> {
            interaction(listOf("MOV", "4AsukaIsBetterThanRei", "MOV"), storageForTest)
        }
    }

    @Test
    fun `test throw error for incomplete input for first argument`() {
        assertThrows<IllegalArgumentException> {
            interaction(listOf("MOV"), storageForTest)
        }
    }

    @Test
    fun `test throw error for incomplete input for second argument`() {
        assertThrows<IllegalArgumentException> {
            interaction(listOf("MOV", "1"), storageForTest)
        }
    }

    /*@ParameterizedTest
    @MethodSource("testForAddingToEnd")
    fun `test for adding to end`(excepted: List<Int>, listOfInt: List<Int>) {
        assertEquals(excepted, listOfInt)
    }*/

    @ParameterizedTest
    @MethodSource("testForAddingToBegin")
    fun `test for adding to begin`(excepted: List<Int>, listOfCommand: List<String>) {
        assertEquals(excepted, interaction(listOfCommand, storageForTest))
    }

    /*@ParameterizedTest
    @MethodSource("testForChange")
    fun `test for change`(excepted: List<Int>, listOfCommand: List<String>) {
        assertEquals(excepted, interaction(listOfCommand, storageForTest))
    }

    @ParameterizedTest
    @MethodSource("testForRevers")
    fun `test for revers`(excepted: List<Int>, listOfCommand: List<String>) {
        assertEquals(excepted, interaction(listOfCommand, storageForTest))
    }*/

    companion object {
        //var storageForTest = PerformedCommandStorage()
        /*@JvmStatic
        fun testForAddingToEnd() = (
            interaction(listOf("TTE", "1"), storageForTest),
            Arguments.of(listOf(1), storageForTest.returnListOfNumbers()),
            /*Arguments.of(listOf(1), interaction(listOf("TTE", "2"), storageForTest)),
            Arguments.of(listOf(1, 2), interaction(listOf("TTE", "3"), storageForTest)),
            Arguments.of(listOf(1, 2, 3), interaction(listOf("TTE", "4"), storageForTest))*/
        )*/

        @JvmStatic
        fun testForAddingToBegin() = listOf(
            Arguments.of(listOf(0, 1, 2, 3, 4), listOf("TTB", "0")),
            Arguments.of(listOf(-1, 0, 1, 2, 3, 4), listOf("TTB", "-1")),
            Arguments.of(listOf(-2, -1, 0, 1, 2, 3, 4), listOf("TTB", "-2")),
            Arguments.of(listOf(-3, -2, -1, 0, 1, 2, 3, 4), listOf("TTB", "-3")),
        )

        @JvmStatic
        fun testForChange() = listOf(
            Arguments.of(listOf(4, -2, -1, 0, 1, 2, 3, -3), listOf("MOV", "0", "7")),
            Arguments.of(listOf(4, 3, -1, 0, 1, 2, -2, -3), listOf("MOV", "1", "6")),
            Arguments.of(listOf(4, 3, 2, 0, 1, -1, -2, -3), listOf("MOV", "2", "5")),
            Arguments.of(listOf(4, 3, 2, 1, 0, -1, -2, -3), listOf("MOV", "3", "4"))
        )

        @JvmStatic
        fun testForRevers() = listOf(
            Arguments.of(listOf(4, 3, 2, 0, 1, -1, -2, -3), listOf("REV")),
            Arguments.of(listOf(4, 3, -1, 0, 1, 2, -2, -3), listOf("REV")),
            Arguments.of(listOf(4, -2, -1, 0, 1, 2, 3, -3), listOf("REV"))
        )
    }
}
