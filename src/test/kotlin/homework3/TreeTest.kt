import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

internal class TreeTest {
    @BeforeEach
    fun `before each`() {
        mapOfInt.clear()
        emptyMapOfInt.clear()
        for (value in 0..10) {
            mapOfInt[listOfIntKeys[value]] = value
            filledInMapForTestPutAll[listOfIntKeys[value]] = value
        }
    }

    @Test
    fun `test for putAll`() {
        emptyMapForTestPutAll.putAll(filledInMapForTestPutAll)
        assertEquals(setOf(21, 2, 32, 4, 51, 6, 72, 8, 19, 10, 1), emptyMapForTestPutAll.keys)
        assertEquals(setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10), emptyMapForTestPutAll.values.toSet())
    }

    @ParameterizedTest
    @MethodSource("testValues")
    fun `test for values of map`(mapOfInt: Tree<Int, Int>, values: Set<Int>) {
        assertEquals(values, mapOfInt.values.toSet())
    }

    @ParameterizedTest
    @MethodSource("testKeys")
    fun `test for keys of map`(mapOfInt: Tree<Int, Int>, keys: Set<Int>) {
        assertEquals(keys, mapOfInt.keys)
    }

    @ParameterizedTest
    @MethodSource("testSize")
    fun `test for size of map`(mapOfInt: Tree<Int, Int>, size: Int) {
        assertEquals(size, mapOfInt.size)
    }

    @ParameterizedTest
    @MethodSource("testPut")
    fun `test for Put`(mapOfInt: Tree<Int, Int>, key: Int, value: Int) {
        mapOfInt[key] = value
        assertEquals(value, mapOfInt[key])
    }

    @ParameterizedTest
    @MethodSource("testIsEmpty")
    fun `test for isEmpty`(excepted: Boolean, mapOfInt: Tree<Int, Int>) {
        assertEquals(excepted, mapOfInt.isEmpty())
    }

    @ParameterizedTest
    @MethodSource("testGet")
    fun `test for Get`(mapOfInt: Tree<Int, Int>, key: Int, value: Int?) {
        assertEquals(value, mapOfInt[key])
    }

    @ParameterizedTest
    @MethodSource("testContainsKey")
    fun `test for containsKey`(excepted: Boolean, mapOfInt: Tree<Int, Int>, key: Int) {
        assertEquals(excepted, mapOfInt.containsKey(key))
    }

    @ParameterizedTest
    @MethodSource("testRemove")
    fun `test for Remove`(value: Int?, mapOfInt: Tree<Int, Int>, key: Int) {
        assertEquals(value, mapOfInt.remove(key))
        assertEquals(false, mapOfInt.containsKey(key))
    }

    @ParameterizedTest
    @MethodSource("testContainsValue")
    fun `test for ContainsValue`(excepted: Boolean, mapOfInt: Tree<Int, Int>, value: Int) {
        assertEquals(excepted, mapOfInt.containsValue(value))
    }

    @ParameterizedTest
    @MethodSource("testClear")
    fun `test for Clear`(mapOfInt: Tree<Int, Int>) {
        mapOfInt.clear()
        assertEquals(0, mapOfInt.size)
    }

    companion object {
        val mapOfInt = Tree<Int, Int>()
        val emptyMapOfInt = Tree<Int, Int>()
        val filledInMapForTestPutAll = Tree<Int, Int>()
        val emptyMapForTestPutAll = Tree<Int, Int>()

        var listOfIntKeys = listOf(21, 2, 32, 4, 51, 6, 72, 8, 19, 10, 1)

        @JvmStatic
        fun testValues() = listOf(
            Arguments.of(mapOfInt, setOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10)),
            Arguments.of(emptyMapOfInt, setOf<Int>())
        )

        @JvmStatic
        fun testKeys() = listOf(
            Arguments.of(mapOfInt, setOf(21, 2, 32, 4, 51, 6, 72, 8, 19, 10, 1)),
            Arguments.of(emptyMapOfInt, setOf<Int>())
        )

        @JvmStatic
        fun testSize() = listOf(
            Arguments.of(mapOfInt, 11),
            Arguments.of(emptyMapOfInt, 0)
        )

        @JvmStatic
        fun testPut() = listOf(
            Arguments.of(mapOfInt, 21, 5),
            Arguments.of(mapOfInt, -4, 5),
            Arguments.of(emptyMapOfInt, 1, 0),
            Arguments.of(emptyMapOfInt, -1, 3)
        )

        @JvmStatic
        fun testIsEmpty() = listOf(
            Arguments.of(false, mapOfInt),
            Arguments.of(true, emptyMapOfInt)
        )

        @JvmStatic
        fun testGet() = listOf(
            Arguments.of(mapOfInt, 2, 1),
            Arguments.of(mapOfInt, 100, null),
            Arguments.of(emptyMapOfInt, 1, null)
        )

        @JvmStatic
        fun testContainsKey() = listOf(
            Arguments.of(true, mapOfInt, 4),
            Arguments.of(true, mapOfInt, 21),
            Arguments.of(false, mapOfInt, 100),
            Arguments.of(false, emptyMapOfInt, 1)
        )

        @JvmStatic
        fun testContainsValue() = listOf(
            Arguments.of(true, mapOfInt, 3),
            Arguments.of(false, mapOfInt, 100),
            Arguments.of(false, emptyMapOfInt, 1)
        )

        @JvmStatic
        fun testClear() = listOf(
            Arguments.of(mapOfInt),
            Arguments.of(emptyMapOfInt)
        )

        @JvmStatic
        fun testRemove() = listOf(
            Arguments.of(0, mapOfInt, 21),
            Arguments.of(null, mapOfInt, 100),
            Arguments.of(null, emptyMapOfInt, 1)
        )
    }
}
