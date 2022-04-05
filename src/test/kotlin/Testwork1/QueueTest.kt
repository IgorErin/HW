import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

internal class QueueTest {
    val emptyQueue = Queue<Int, Int>() { a: Pair<Int, Int>, b: Pair<Int, Int> -> a.second - b.second }
    val firstQueue = Queue<Int, Int>() { a: Pair<Int, Int>, b: Pair<Int, Int> -> a.second - b.second }

    @BeforeEach
    fun before() {
        for (item in 1..5) {
            firstQueue.enqueue(item, item)
        }
    }

    @Test
    fun `work with epmty queue`() {
        val exception = assertFailsWith<IllegalArgumentException> { emptyQueue.remove() }
        assertEquals("empty queue", exception.message)
    }

    @Test
    fun `test fot peek`() {
        assertEquals(5, firstQueue.peek())
    }

    @Test
    fun `test fot remove`() {
        firstQueue.remove()
        assertEquals(5, firstQueue.peek())
    }

    @Test
    fun `test fot roll`() {
        assertEquals(1, firstQueue.roll())
    }
}
