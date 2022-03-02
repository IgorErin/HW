import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class Task_1_2Test {
    @Test
    fun testPrime() {
        assertEquals(listOf(2, 3, 5, 7, 11), createListOfPrime(11))
    }

    @Test
    fun testComposite() {
        assertEquals(listOf(2, 3, 5, 7, 11, 13), createListOfPrime(15))
    }

    @Test
    fun negativeNumber() {
        assertEquals(emptyList<Int>(), createListOfPrime(-4))
    }

    @Test
    fun lessThenRequired() {
        assertEquals(emptyList<Int>(), createListOfPrime(1))
    }
}
